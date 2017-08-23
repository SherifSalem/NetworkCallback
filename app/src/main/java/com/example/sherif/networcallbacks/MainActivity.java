package com.example.sherif.networcallbacks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.util.Log.d;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView listView;
    private static int POST_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = (Button) findViewById(R.id.view_post);
        listView = (TextView) findViewById(R.id.mylistview);

    }

    public void ViewPost(View view) {
            d("ViewPost", "view post");

         new RetrieveFeedTask(listView).execute();


        }


    }

