package com.example.sherif.networcallbacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
        listView = (ListView) findViewById(R.id.mylistview);




        Log.d("New new ", "New new");
    }




    public void ViewPost(View view) {
            d("ViewPost", "view post");

             new RetrieveFeedTask(listView).execute();

        }

        @Override
    public void onActivityResult (int requestCode, int resultCode,Intent data){
            super.onActivityResult(requestCode,resultCode,data);

            try{
                if (requestCode == POST_REQUEST && resultCode == RESULT_OK && data!=null){


                }
            }catch (Exception e) {
                Toast.makeText(getApplicationContext(),"post not found", Toast.LENGTH_SHORT).show();
            }
        }

    }

