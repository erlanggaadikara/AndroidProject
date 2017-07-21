package com.example.erlangga.finalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erlangga on 22/05/2017.
 */

public class ListViewAdapter extends BaseAdapter{
    private final Context context;
    private List<Movies> urls = new ArrayList<Movies>();


    public ListViewAdapter(Context context, List<Movies> urls) {
        this.context = context;
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Movies getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_poster, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        // Get the image URL for the current position.
        Movies movies = getItem(position);


        Picasso.with(context) //
                .load(movies.getPoster_path()) //
                .placeholder(R.drawable.ic_hourglass_empty_black_36dp) //
                .error(R.drawable.ic_error_black_36dp) //
                .fit() //
                .tag(context) //
                .into(imageView);



        return convertView ;
    }
}
