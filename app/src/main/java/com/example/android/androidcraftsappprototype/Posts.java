package com.example.android.androidcraftsappprototype;

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
    WSAdapter.SendAPIRequests PostsHelper;
    StringBuilder postsBuffer = new StringBuilder();

    @Override
    protected void onResume(){
        super.onResume();
        PostsDetails postDetailsHelper = new PostsDetails();
        postDetailsHelper.ListPosts();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        PostsDetails postDetailsHelper = new PostsDetails();

        postsDoneBtn = (Button) findViewById(R.id.PostsDoneButton);

        postDetailsHelper.callPostDetails("192.168.0.18:8000/api");
        postDetailsHelper.ListPosts();
        postDetailsHelper.postDetailsCalled('n');

        postsDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Posts.this, MainActivity.class));

            }
        });
    }

    public class PostsDetails {
        //String post_title, post_content;
        ArrayList<Integer> post_id = new ArrayList<Integer>();
        ArrayList<String> post_title = new ArrayList<String>();
        ArrayList<String> post_content = new ArrayList<String>();

        boolean isPDCalled;

        // sets if Post details are called
        boolean postDetailsCalled(char called) {
            if (called == 'y'){
                return true;
            }
            return false;
        }

        // checks if postsDetails functions are called for AsyncTask
        boolean getIsPDCalled(){
            return isPDCalled;
        }

        // calls the execute for AsyncTask
        private void callPostDetails(String theurl){
            PostsHelper = new WSAdapter.SendAPIRequests();
            // sets if post details are called
            postDetailsCalled('y');
            // executes AsyncTask
            PostsHelper.execute(theurl);
        }

        // sets values for the posts arrays
        public void setPost(int p_id, String p_title, String p_content) {
            post_id.add(p_id);
            post_title.add(p_title);
            post_content.add(p_content);
        }

        // Lists the posts from the database
        public void ListPosts() {
            /////////// add functionality if a post was deleted and was clicked
            postsSect = (TextView) findViewById(R.id.PostsSection);
            postsSect.setText(post_title.get(post_title.size()) + "\n");
            for (int i = post_id.size() - 1; i > 0; i--)
            {
                postsSect.append(post_title.get(i));
            }
        }
    }
}
