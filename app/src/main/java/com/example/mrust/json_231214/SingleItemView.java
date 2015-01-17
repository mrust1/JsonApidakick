package com.example.mrust.json_231214;

/**
 * Created by mrust on 17.01.2015.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SingleItemView extends Activity {
    // Declare Variables
    String name;
    String medium;
    String ticket_url;
    String start_datetime;
    String end_datetime;
    String phone;
    String latitude;
    String longtitude;
    ProgressDialog mProgressDialog;
    Bitmap bmImg = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.singleitemview);
        // Execute loadSingleView AsyncTask
        new loadSingleView().execute();
    }

    public class loadSingleView extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(SingleItemView.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Muzik Sanat Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();

        }

        @Override
        protected String doInBackground(String... args) {
            try {
                // Retrieve data from ListViewAdapter on click event
                Intent i = getIntent();

                // get the result of name
                name =  i.getStringExtra("name");
                // get the result of ticket url
                ticket_url = i.getStringExtra("ticket_url");

                start_datetime = i.getStringExtra("start_datetime");
                end_datetime = i.getStringExtra("end_datetime");
                phone = i.getStringExtra("phone");
                longtitude = i.getStringExtra("longtitude");
                latitude = i.getStringExtra("latitude");
                medium = i.getStringExtra("medium");


                // Download the Image from the result URL given by flag
                URL url = new URL(medium);
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.setDoInput(true);
                conn.connect();
                InputStream is = conn.getInputStream();
                bmImg = BitmapFactory.decodeStream(is);
            } catch (IOException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String args) {
            // Locate the TextViews in listview_item.xml
           TextView txtname = (TextView) findViewById(R.id.name);
            TextView txtticket_url = (TextView) findViewById(R.id.ticket_url);
            TextView txtstart_datetime = (TextView) findViewById(R.id.start_datetime);
            TextView txtend_datetime = (TextView) findViewById(R.id.end_datetime);
            TextView txtphone = (TextView) findViewById(R.id.phone);
            ImageView imgmedium = (ImageView) findViewById(R.id.images);
            TextView txtlongitude = (TextView)findViewById(R.id.locations_longitude);
            TextView txtlatitude = (TextView) findViewById(R.id.locations_latitude);



            // Set results to the TextViews
            txtname.setText(name);
            txtticket_url.setText(ticket_url);
            txtstart_datetime.setText(start_datetime);
            txtend_datetime.setText(end_datetime);
            txtphone.setText(phone);
            txtlatitude.setText(latitude);
            txtlongitude.setText(longtitude);

            // Set results to the ImageView
            imgmedium.setImageBitmap(bmImg);
            // Close the progressdialog
            mProgressDialog.dismiss();

        }
    }

}