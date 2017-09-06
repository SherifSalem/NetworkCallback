package com.example.sherif.networcallbacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by sherif on 06/09/17.
 */

public class CommentLayout extends AppCompatActivity {

    private TextView usrId;
    private TextView comId;
    private TextView titleName;
    private TextView bodyString;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_layout);

        usrId = (TextView) findViewById(R.id.user_id);
        comId = (TextView) findViewById(R.id.comment_id);
        titleName = (TextView) findViewById(R.id.topic);
        bodyString = (TextView) findViewById(R.id.body);

        Intent intent = getIntent();
        String uID = intent.getStringExtra("UserID");
        String cID = intent.getStringExtra("CommentID");
        String cTitle = intent.getStringExtra("Title");
        String cBody = intent.getStringExtra("Comment");

        usrId.setText(uID);
        comId.setText(cID);
        titleName.setText(cTitle);
        bodyString.setText(cBody);

    }







}
