package com.example.mrust.json_231214;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {
    // Declare Variables
    JSONObject jsonobject;
    JSONArray jsonarray;
    EditText etResponse;
    TextView tvIsConnected;
    ListView listview;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;

    static String NAME = "name";
    static String TICKET_URL = "ticket_url";
    static String START_DATETIME = "start_datetime";
    static String END_DATETIME = "end_datetime";
    static String PHONE = "phone";
    static String LATITUDE = "latitude";
    static String LONGITUDE = "longitude";
    static String MEDIUM = "medium";
    static String ANDROID = "android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout. listview_main);
        // get reference to the viewsctkya@
        etResponse = (EditText) findViewById(R.id.etResponse);
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);

        // check if you are connected or not
        if (isConnected()) {
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are connected");
        } else {
            tvIsConnected.setText("You are NOT connected");
        }

        new HttpAsyncTask().execute();
    }

    // network connected check option
    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }


    private class HttpAsyncTask extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Muzik Sanat Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            //mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }


        @Override
        protected String doInBackground(Void... params) {
            arraylist = new ArrayList<HashMap<String, String>>();
            jsonobject = JSONfunctions.getJSONfromURL("https://api.dakick.com/api/v1/events?page=1&per_page=1");

            try {
                jsonarray = jsonobject.getJSONArray("events");
                //
                // New Model
                for (int i = 0; i < jsonarray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonobject= jsonarray.getJSONObject(i);
                    //
                    // New Model
                    map.put("name", jsonobject.getString("root_meta_name"));
                    map.put("ticket_url", jsonobject.getString("ticket_url"));
                    map.put("start_datetime", jsonobject.getString("start_datetime"));
                    map.put("end_datetime", jsonobject.getString("end_datetime"));
                    map.put("phone", jsonobject.getString("phone"));

                    JSONObject ja = jsonobject.getJSONObject("location_or_broadcast_geo");
                    if (ja != null) {
                        // kordinatları alıyor longtitude, latitude
                        map.put("latitude", ja.getString("latitude"));
                        map.put("longitude", ja.getString("longitude"));
                    }
                    JSONObject jo_images = jsonobject.getJSONObject("images");
                    jo_images = jo_images.getJSONObject("medium");
                    map.put("medium", jo_images.getString("path"));
                    arraylist.add(map);
                }


            }
            catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }

        // onPostExecute displays the results of the AsyncTask.

        protected void onPostExecute(Void args) {
            // Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            //etResponse.setText(result);
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listview);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(MainActivity.this, arraylist);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }
}