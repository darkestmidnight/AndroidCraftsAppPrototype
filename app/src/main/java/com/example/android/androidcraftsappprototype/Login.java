package com.example.android.androidcraftsappprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    Button LoginButton, RegButton;
    EditText uUserName, uPassWord;
    WSAdapter.SendAPIRequests AuthHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //SetupHomeBtn = (ImageButton) findViewById(R.id.SetupHomeBtn);

        LoginButton = (Button) findViewById(R.id.LoginButton);
        RegButton = (Button) findViewById(R.id.LoginRegister);

        uUserName = (EditText) findViewById(R.id.LoginUserBox);
        uPassWord = (EditText) findViewById(R.id.LoginPassBox);

        //AuthHelper = new WSAdapter().new SendDeviceDetails();

        // Moves user to the main page after validation
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gets the username and password from the EditText
                String strUserName = uUserName.getText().toString();
                String strPassWord = uPassWord.getText().toString();

                // API url duh
                String APIUrl = "http://192.168.0.18:8000/token-auth/";

                // If the user is authenticated, then transfer to the MainActivity page
                if (APIAuthentication(strUserName, strPassWord, APIUrl)){
                    startActivity(new Intent(Login.this, Posts.class));
                }
            }
        });

        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gets the username and password from the EditText
                startActivity(new Intent(Login.this, Register.class));
            }
        });

    }

    private boolean APIAuthentication(String un, String pw, String url){
        // when it wasn't static -> AuthHelper = new WSAdapter().new SendAPIRequests();
        AuthHelper = new WSAdapter.SendAPIRequests();

        JSONObject postData = new JSONObject();
        try {
            // Attempt to input info to the Django API
            postData.put("username", un);
            postData.put("password", pw);

            // Putting the data to be posted in the Django API
            AuthHelper.execute(url, postData.toString());

            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
