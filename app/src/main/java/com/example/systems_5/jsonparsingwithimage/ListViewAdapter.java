package com.example.systems_5.jsonparsingwithimage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DHANRAJ NAIK
 */
/*
In this custom listview adapter class,
string arrays are passed into the ListViewAdapter and set into the TextViews and ImageViews
followed by the positions. On listview item click will pass the string arrays and position to a new activity.
 */
public class ListViewAdapter extends BaseAdapter {

    //declare variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String,String>> data;
    ImageLoader imageLoader;
    HashMap<String,String> resultp =new HashMap<String,String>();

    public ListViewAdapter(Context context,ArrayList<HashMap<String,String>> arraylist){
        this.context=context;
        data=arraylist;
        imageLoader = new ImageLoader(context);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //declare variables
        TextView rank;
        TextView country;
        TextView population;
        ImageView flag;


        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView =inflater.inflate(R.layout.listview_item,parent,false);

        //get the position
        resultp =data.get(position);

        // Locate the TextViews in listview_item.xml
        rank = (TextView) itemView.findViewById(R.id.rank);
        country = (TextView) itemView.findViewById(R.id.country);
        population = (TextView) itemView.findViewById(R.id.population);

        // Locate the ImageView in listview_item.xml
        flag = (ImageView) itemView.findViewById(R.id.flag);

        // Capture position and set results to the TextViews
        rank.setText(resultp.get(MainActivity.RANK));
        country.setText(resultp.get(MainActivity.COUNTRY));
        population.setText(resultp.get(MainActivity.POPULATION));
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(resultp.get(MainActivity.FLAG), flag);

        //capture listview item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the position
                resultp = data.get(position);
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data rank
                intent.putExtra("rank", resultp.get(MainActivity.RANK));
                // Pass all data country
                intent.putExtra("country", resultp.get(MainActivity.COUNTRY));
                // Pass all data population
                intent.putExtra("population",resultp.get(MainActivity.POPULATION));
                // Pass all data flag
                intent.putExtra("flag", resultp.get(MainActivity.FLAG));
                // Start SingleItemView Class
                context.startActivity(intent);
            }
        });

        return itemView;
    }
}
