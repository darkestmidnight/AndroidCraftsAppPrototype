package com.example.android.androidcraftsappprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.TextView;


// I forgot what WS stands for, but this class serves as an adapter for JSON and Online stuff
// I think it stands for With-Server Adapter
public class WSAdapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    static public class SendAPIRequests extends AsyncTask<String, String, String> {

        // Add a pre-execute thing

        @Override
        protected String doInBackground(String... params) {

            Log.e("TAG", params[0]);
            Log.e("TAG", params[1]);
            String data = "";

            HttpURLConnection httpURLConnection = null;
            try {

                // Sets up connection to the URL (params[0] from .execute in "login")
                httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();

                // Sets the request method for the URL
                httpURLConnection.setRequestMethod("POST");

                // Tells the URL that I am sending a POST request body
                httpURLConnection.setDoOutput(true);

                // To write primitive Java data types to an output stream in a portable way
                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                // Writes out a byte to the underlying output stream of the data posted from .execute function
                wr.writeBytes("postData=" + params[1]);
                // Flushes the postData to the output stream
                wr.flush();
                wr.close();

                // Representing the input stream
                InputStream in = httpURLConnection.getInputStream();

                // Preparing input stream bytes to be decoded to charset
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                StringBuilder dataBuffer = new StringBuilder();

                // Translates input stream bytes to charset
                int inputStreamData = inputStreamReader.read();
                while (inputStreamData != -1) {
                    char current = (char) inputStreamData;
                    inputStreamData = inputStreamReader.read();
                    // concatenates data characters from input stream
                    dataBuffer.append(current);
                }
                data = dataBuffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Disconnects socket after using
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }

            Log.e("TAG", data);
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // expecting a response code fro my server upon receiving the POST data
            Log.e("TAG", result);

            /*Posts.PostsDetails postsHelper = new Posts().new PostsDetails();

            // For posts
            try {
                if (postsHelper.getIsPDCalled()){
                    JSONObject pJObj = new JSONObject(result);
                    JSONArray pJObjArray = pJObj.getJSONArray("posts");

                    for (int i = 0; i < pJObjArray.length(); i++) {
                        JSONObject pJObj_data = pJObjArray.getJSONObject(i);
                        postsHelper.setPost(pJObj_data.getInt("id"), "post_title", "post_content");
                    }
                }

            } catch (JSONException e) {
                //Toast.makeText(JSonActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                Log.d("Json","Exception = "+e.toString());
            }*/
        }
    }

    public class SendPostsRequest extends AsyncTask<String, String, String> {
        TextView postsSect;

        // Add a pre-execute thing
        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... params) {

            StringBuilder result = new StringBuilder();

            try {
                urlConnection = (HttpURLConnection) new URL(params[0]).openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

            }catch( Exception e) {
                e.printStackTrace();
            }
            /*finally {
                urlConnection.disconnect();
            }*/

            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {

            // expecting a response code fro my server upon receiving the POST data
            Log.e("TAG", result);

            Posts.PostsDetails postsHelper = new Posts().new PostsDetails();

            // For posts
            try {
                /*JSONObject pJObj = new JSONObject(result);
                JSONArray pJObjArray = pJObj.getJSONArray(result);*/
                JSONArray pJObjArray = new JSONArray(result);

                // algorithm for parsing the JSONArray from the Django REST API
                for (int i = 0; i < pJObjArray.length(); i++) {
                    // puts the current iterated JSON object from the array to another temporary object
                    JSONObject pJObj_data = pJObjArray.getJSONObject(i);
                    // inputs necesarry elements to the ListPosts function
                    postsHelper.setPost(pJObj_data.getInt("id"), pJObj_data.getString("post_title"), pJObj_data.getString("post_content"));
                }

            } catch (JSONException e) {
                //Toast.makeText(JSonActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                Log.d("Json","Exception = "+e.toString());
            }

            postsHelper.ListPosts();
        }

        /*protected void onProgressUpdate(String... string) {

            .settext(string[0]); // and here you set the text

        }*/

    }
}
