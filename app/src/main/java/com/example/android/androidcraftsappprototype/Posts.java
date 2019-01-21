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
        PostsDetails postDetailsHelper = new PostsDetails();
        //postDetailsHelper.ListPosts();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        postsDoneBtn = (Button) findViewById(R.id.PostsDoneButton);
        postsSect = (TextView) findViewById(R.id.PostsSection);

        PostsDetails postDetailsHelper = new PostsDetails();

        PostsHelper = new WSAdapter().new SendPostsRequest(this, this);
        PostsHelper.execute("http://192.168.0.18:8000/api/");

        //postDetailsHelper.callPostDetails("http://192.168.0.18:8000/api/");
        //postDetailsHelper.ListPosts();

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

        // checks if postsDetails functions are called for AsyncTask
        boolean getIsPDCalled(){
            return isPDCalled;
        }

        // calls the execute for AsyncTask
        private void callPostDetails(String theurl){
            //PostsHelper = new WSAdapter().new SendPostsRequest(this);
            // executes AsyncTask
            PostsHelper.execute(theurl);
        }

        // sets values for the posts arrays
        public void setPost(int p_id, String p_title, String p_content) {
            this.post_id.add(p_id);
            this.post_title.add(p_title);
            this.post_content.add(p_content);
        }

        public ArrayList<Integer> getPostID() {
            return this.post_id;
        }

        public ArrayList<String> getPostTitle() {
            return this.post_title;
        }

        public ArrayList<String> getPostContent() {
            return this.post_content;
        }

        // Lists the posts from the database
        public void ListPosts() {
            /////////// add functionality if a post was deleted and was clicked
            postsSect = (TextView) findViewById(R.id.PostsSection);

            int lastFrJSONArray = getPostID().size() - 1;

            // outputs the id of the very first post, something to put to the textview
            postsSect.setText("id: " + getPostID().get(0) + "\n");
            for (int i = lastFrJSONArray; i >= 0; i--)
            {
                // appending the titles and contents of the current post
                postsSect.append("title: " + getPostTitle().get(i) + "\n");
                postsSect.append("content: " + getPostContent().get(i) + "\n");

                // if this is the last post, then don't need to append id for the next post.
                if (i != 0) {
                    postsSect.append("id: " + getPostID().get(i) + "\n");
                }
            }
        }
    }
}
