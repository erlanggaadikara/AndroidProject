package com.example.erlangga.finalproject;


import android.content.Intent;

import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailFilm extends AppCompatActivity {

    TextView tahun;
//    TextView rilis;
//    TextView durasi;
    TextView judul;

    TextView sinopsis;

    ImageView img1;
    int id = 0;
    Button btn;
    ImageView btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        final Intent intent = getIntent();

        judul = (TextView)findViewById(R.id.txt_1);
        sinopsis = (TextView)findViewById(R.id.sinopsis);
        tahun = (TextView)findViewById(R.id.txt_2);
//        rilis = (TextView)findViewById(R.id.txt_3);
        img1 = (ImageView)findViewById(R.id.film_pic);
        btn = (Button)findViewById(R.id.trailer);
        btn2 = (ImageView) findViewById(R.id.fav);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setAutoMeasureEnabled(true);

        Picasso.with(getApplicationContext())
                .load(intent.getStringExtra("poster_path"))
                .fit()
                .into(img1);

        final String release = intent.getStringExtra("year");
        final String mainjudul = intent.getStringExtra("title");
        tahun.setText(release.split("-")[0]);
        judul.setText(intent.getStringExtra("title"));
        //rilis.setText("Release on " + intent.getStringExtra("release"));
        sinopsis.setText(intent.getStringExtra("sinopsis"));
        id = intent.getIntExtra("id", 0);

        final String API_KEY="?api_key=[APIKEYDBMOVIE]";

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               Intent intent1 = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/results?search_query="+mainjudul));
                startActivity(intent1);
            }

        });

        final List<Favorite> favorite = Favorite.find(Favorite.class, "movie_id = ?", String.valueOf(id));

        if (favorite.size() > 0) {
            if (btn2 != null) {
                btn2.setImageResource(R.drawable.ic_star_512);
            }

        }

        if(btn2 != null) {
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<Favorite> favoriteList = Favorite.find(Favorite.class, "movie_id = ?", String.valueOf(id));
                    if (favoriteList.size() <= 0) {

                        Favorite favorite_store = new Favorite();
                        favorite_store.setMovieId(id);
                        favorite_store.setNote(release);
                        favorite_store.save();
                        Snackbar.make(v, "This movie has been add to your favorite", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        btn2.setImageResource(R.drawable.ic_star_512);
                    } else {
                        Favorite single_favorite = favoriteList.get(0);
                        single_favorite.delete();

                        Snackbar.make(v, "This movie has been remove from your favorite", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        btn2.setImageResource(R.drawable.ic_star_511);
                    }
                }
            });
        }
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}

