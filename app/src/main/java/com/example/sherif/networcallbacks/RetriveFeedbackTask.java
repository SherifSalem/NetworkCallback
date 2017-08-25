package com.example.sherif.networcallbacks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.R.id.list;
import static android.R.id.list_container;

/**
 * Created by sherif on 18/08/17.
 */

class  RetrieveFeedTask extends AsyncTask<Void, Void, String> {

         ListView responseView;
        ProgressBar progressBar;



        public RetrieveFeedTask (ListView listView)
        {
                responseView = listView;
        }

protected void onPreExecute() {
        //progressBar.setVisibility(View.VISIBLE);
        responseView.setText("");

        }

protected String doInBackground(Void... urls) {

        // Do some validation here

        try {
        URL url = new URL("https://jsonplaceholder.typicode.com/posts" );
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
        stringBuilder.append(line).append("\n");
        }
        bufferedReader.close();

        return stringBuilder.toString();

        }
        finally{
        urlConnection.disconnect();
        }
        }
        catch(Exception e) {
        Log.e("ERROR", e.getMessage(), e);
        return null;
        }
        }

protected void onPostExecute(String response) {
        if(response == null) {
        response = "THERE WAS AN ERROR";
        }

      // progressBar.setVisibility(View.GONE);
        Log.i("INFO", response);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,list,);
        try {

             JSONArray jsonArray = new JSONArray(response);
               responseView.setAdapter(adapter);
                for (int i = 0; i<jsonArray.length();i++) {
                        jsonArray.getString(jsonArray.getInt(0));
                }
        } catch (JSONException e) {
                e.printStackTrace();
        }

        }

        }
