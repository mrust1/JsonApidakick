package com.example.mrust.json_231214;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends Activity {

    static EditText etResponse;
    TextView tvIsConnected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get reference to the views
        etResponse = (EditText) findViewById(R.id.etResponse);
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);

        // check if you are connected or not
        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are conncted");
        }
        else{
            tvIsConnected.setText("You are NOT conncted");
        }

        // call AsynTask to perform network operation on separate thread
        //new HttpAsyncTask().execute("http://hmkcode.appspot.com/rest/controller/get.json");
        //new HttpAsyncTask().execute("http://api.openweathermap.org/data/2.5/weather?q=Londra");
        new HttpAsyncTask().execute("https://api.dakick.com/api/v1/events?page=1&per_page=1");

    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    // converto Input Stream To String.
    // Converto Input Strem to Json
    private static String convertInputStreamToString(InputStream inputStream) throws IOException, JSONException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line ;

        JSONObject json = new JSONObject(result);
        //JSONArray jarray = json.getJSONArray("events");
        //result = jarray.getJSONObject(1);
        //etResponse.setText(json.toString());
        //result = jarray.toString();
        //results = json.getString("events");
        // events Array
        JSONArray articles = json.getJSONArray("events");
        /*result = articles.getJSONObject(0).getString("id") +"  ";
        result += articles.getJSONObject(0).getString("slug") +"  ";
        result += articles.getJSONObject(0).getString("slug")+"  ";
        result += articles.getJSONObject(0).getString("name")+ "  ";
        result += articles.getJSONObject(0).getString("description")+ "  ";
        result += articles.getJSONObject(0).getString("start_date_time")+"  ";
        result += articles.getJSONObject(0).getString("end_date_time")+"  \n";
        */
        //JSONObject articles_geo = json.getJSONObject("location_or_broadcast_geo");
        result = articles.getString(0);
        //result = String.valueOf(json.getJSONObject("location_or_broadcast_geo"));
        //result = String.valueOf((articles_geo.getLong("latitude")));
        //result = articles_geo.getJSONObject(0).getString("latitude")+"   ";
        //result += articles_geo.getJSONObject(0).getString("longitude")+"   ";


        inputStream.close();
        return result;

    }

    // network connected check option
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            etResponse.setText(result);
        }
    }
}