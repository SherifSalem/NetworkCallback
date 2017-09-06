package com.example.sherif.networcallbacks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
import java.util.ArrayList;

/**
 * Created by sherif on 18/08/17.
 */

class  RetrieveFeedTask extends AsyncTask<Void, Void, String> {

         ListView responseView;
    ListViewAdapter adapter ;
    ArrayList<CommentsList> list = new ArrayList<CommentsList>();

    ProgressBar progressBar;
    Context context;



        public RetrieveFeedTask (ListView listView, Context  ctx)
        {
                responseView = listView;
            context  = ctx ;
            adapter = new ListViewAdapter();
            responseView.setAdapter(adapter);
        }

protected void onPreExecute() {
        //progressBar.setVisibility(View.VISIBLE);
        //responseView.setText("");

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

        Log.d("response", "response");
        try {

             JSONArray jsonArray = new JSONArray(response);

                       for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject u = jsonArray.getJSONObject(i);
                        String userId = u.getString("userId");
                       String id = u.getString("id");
                       String title = u.getString("title");
                       String body = u.getString("body");

                           CommentsList c = new CommentsList(title,body,userId,id,i);
                          list.add(c);





                                  }

                                  adapter.notifyDataSetChanged();


        } catch (JSONException e) {
                e.printStackTrace();
        }

        }
    class ListViewAdapter extends BaseAdapter {




        @Override
        public int getCount() {

            return list.size();

        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }


        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = ((Activity)context).getLayoutInflater().inflate(R.layout.custom_listview, null);
            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            TextView textView_title = (TextView)view.findViewById(R.id.title);
            TextView textView_description = (TextView)view.findViewById(R.id.description);



            imageView.setImageResource(R.mipmap.ic_launcher);

          final CommentsList model =   list.get(i);
            textView_title.setText(model.getTitle());
            textView_description.setText(model.getDescription());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,CommentLayout.class);
                    intent.putExtra("UserID",model.getUserId());
                    intent.putExtra("CommentID",model.getId());
                    intent.putExtra("Title",model.getTitle());
                    intent.putExtra("Comment",model.getDescription());
                    context.startActivity(intent);
                }
            });


            return view;
        }
    }


}
