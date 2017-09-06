package com.example.sherif.networcallbacks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    ListView listView;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = (Button) findViewById(R.id.view_post);
        listView = (ListView) findViewById(R.id.mylistview);



    }


    public void ViewPost(View view) {

        new RetrieveFeedTask(listView,this).execute();
    }


    }

