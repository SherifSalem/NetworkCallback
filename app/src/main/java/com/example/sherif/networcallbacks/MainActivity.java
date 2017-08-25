package com.example.sherif.networcallbacks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import static android.util.Log.d;

public class MainActivity extends AppCompatActivity {
    Button btn;
    ListView listView;
    private static int POST_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = (Button) findViewById(R.id.view_post);
        textView = (ListView) findViewById(R.id.mylistview);

    }
    class ListViewAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.custom_listview,null);
            ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
            TextView textView_title = (TextView)view.findViewById(R.id.title);
            TextView textView_description= (TextView)view.findViewById(R.id.description);

            imageView.setImageResource(i);


            return null;
        }
    }

    public void ViewPost(View view) {
            d("ViewPost", "view post");

         new RetrieveFeedTask(textView).execute();



        }


    }

