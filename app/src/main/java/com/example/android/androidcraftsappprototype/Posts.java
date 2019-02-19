package com.example.android.androidcraftsappprototype;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Posts extends AppCompatActivity {

    TextView postsSect;
    Button postsDoneBtn;
    WSAdapter.SendPostsRequest PostsHelper;
    StringBuilder postsBuffer = new StringBuilder();

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        postsDoneBtn = (Button) findViewById(R.id.PostsDoneButton);
        postsSect = (TextView) findViewById(R.id.PostsSection);

        PostsHelper = new WSAdapter().new SendPostsRequest(this, this);
        PostsHelper.execute("http://192.168.0.18:8000/api/profile/");

        //postDetailsHelper.callPostDetails("http://192.168.0.18:8000/api/");
        //postDetailsHelper.ListPosts();

        postsDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Posts.this, MainActivity.class));

            }
        });
    }

    public class PostsAttributes {
        int post_id;
        String post_title;
        String post_content;

        // sets values for the posts
        PostsAttributes(int p_id, String p_title, String p_content) {
            this.post_id = p_id;
            this.post_title = p_title;
            this.post_content = p_content;
        }

        public int getPostId() {
            return this.post_id;
        }

        public String getPostTitle() {
            return this.post_title;
        }

        public String getPostContent() {
            return this.post_content;
        }

    }
}
