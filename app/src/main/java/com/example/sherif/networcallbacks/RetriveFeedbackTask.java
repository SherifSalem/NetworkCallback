package com.example.sherif.networcallbacks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
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
            makeTextViewResizable(textView_description,1,"See more",true);

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
   public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else {
                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }
    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                           final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {


            ssb.setSpan(new MySpannable(false){
                @Override
                public void onClick(View widget) {
                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, "See Less", false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, 3, ".. See More", true);
                    }
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }


}
