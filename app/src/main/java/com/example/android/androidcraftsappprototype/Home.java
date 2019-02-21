package com.example.android.androidcraftsappprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class Home extends AppCompatActivity {

    EditText postTitle, postContent;
    Button submitPostBtn, goToProfileBtn;
    WSAdapter.CreatePostRequests CreatePostsHelper;

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        postTitle = (EditText) findViewById(R.id.postTitle);
        postContent = (EditText) findViewById(R.id.postContent);
        submitPostBtn = (Button) findViewById(R.id.cpSubmitBtn);
        goToProfileBtn = (Button) findViewById(R.id.cpProfileBtn);

        CreatePostsHelper = new WSAdapter().new CreatePostRequests(this);

        submitPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gets the username and password from the EditText
                String strPostTitle = postTitle.getText().toString();
                String strPostContent = postContent.getText().toString();

                // API url duh
                String APIUrl = "http://192.168.0.18:8000/api/home/";
                // starts the AsyncTask CreatePostsHelper
                CreatePostsHelper.execute(strPostTitle, strPostContent, APIUrl);
                // Goes to Posts Page after submit is clicked
                startActivity(new Intent(Home.this, Posts.class));
            }
        });

        goToProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Goes to Posts Page after gotoProfile is clicked
                startActivity(new Intent(Home.this, Posts.class));
            }
        });

    }
}
