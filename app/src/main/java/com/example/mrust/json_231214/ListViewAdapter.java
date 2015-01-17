package com.example.mrust.json_231214;

/**
 * Created by mrust on 17.01.2015.
 */

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

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;

    public ListViewAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
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

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView name;
        TextView ticket_url;
        TextView start_datetime;
        TextView end_datetime;
        TextView phone;
        TextView latitude;
        TextView longitude;
        ImageView medium;
        ImageView android;


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listview_item, parent, false);
        // Get the position from the results
        HashMap<String, String> resultp = new HashMap<String, String>();
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        name = (TextView) itemView.findViewById(R.id.name);
        ticket_url = (TextView) itemView.findViewById(R.id.ticket_url);
        start_datetime = (TextView) itemView.findViewById(R.id.start_datetime);
        end_datetime = (TextView) itemView.findViewById(R.id.end_datetime);
        phone = (TextView) itemView.findViewById(R.id.phone);
        medium = (ImageView) itemView.findViewById(R.id.images);
        longitude = (TextView) itemView.findViewById(R.id.locations_longitude);
        latitude = (TextView) itemView.findViewById(R.id.locations_latitude);
        android = (ImageView) itemView.findViewById(R.id.images);

        // Capture position and set results to the TextViews

        name.setText(resultp.get(MainActivity.NAME));
        ticket_url.setText(resultp.get(MainActivity.TICKET_URL));
        start_datetime.setText(resultp.get(MainActivity.START_DATETIME));
        end_datetime.setText(resultp.get(MainActivity.END_DATETIME));
        phone.setText(resultp.get(MainActivity.PHONE));
        longitude.setText(resultp.get(MainActivity.LONGITUDE));
        latitude.setText(resultp.get(MainActivity.LATITUDE));
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class to download and cache
        // images
        imageLoader.DisplayImage(resultp.get(MainActivity.MEDIUM), medium);

        // Capture button clicks on ListView items
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position from the results
                HashMap<String, String> resultp = new HashMap<String, String>();
                resultp = data.get(position);
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(context, SingleItemView.class);

                intent.putExtra("name",resultp.get(MainActivity.NAME));
                intent.putExtra("ticket_url" , resultp.get(MainActivity.TICKET_URL));
                intent.putExtra("start_datetime" , resultp.get(MainActivity.START_DATETIME));
                intent.putExtra("end_datetime" , resultp.get(MainActivity.END_DATETIME));
                intent.putExtra("phone", resultp.get(MainActivity.PHONE));
                intent.putExtra("Longitude", resultp.get(MainActivity.LONGITUDE));
                intent.putExtra("latitude" , resultp.get(MainActivity.LATITUDE));
                intent.putExtra("medium", resultp.get(MainActivity.MEDIUM));

                // Start SingleItemView Class
                context.startActivity(intent);

            }
        });

        return itemView;
    }
}
