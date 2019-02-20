package com.example.android.androidcraftsappprototype;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    Button regSbmtBtn;
    EditText regFirstN, regLastN, regUserN, regPass, regEmail;
    WSAdapter.SendRegisterRequests RegisterHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regSbmtBtn = (Button) findViewById(R.id.RegSubmitBtn);

        regFirstN = (EditText) findViewById(R.id.RegFirstName);
        regLastN = (EditText) findViewById(R.id.RegLastName);
        regUserN = (EditText) findViewById(R.id.RegUsername);
        regPass = (EditText) findViewById(R.id.RegPassword);
        regEmail = (EditText) findViewById(R.id.RegEmail);

        regSbmtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // gets the input from the register textboxes
                String strFirstN = regFirstN.getText().toString();
                String strLastN = regLastN.getText().toString();
                String strUserN = regUserN.getText().toString();
                String strPass = regPass.getText().toString();
                String strEmail = regEmail.getText().toString();


                // API url duh
                String APIUrl = "http://192.168.0.18:8000/api/register/";

                // registers the account
                RegisterAccount(strUserN, strPass, strEmail, strFirstN, strLastN, APIUrl);

                startActivity(new Intent(Register.this, Login.class));
            }
        });


    }

    private void RegisterAccount(String un, String pw, String email, String fn, String ln, String url){
        // when it wasn't static -> AuthHelper = new WSAdapter().new SendAPIRequests();
        RegisterHelper = new WSAdapter().new SendRegisterRequests();
        RegisterHelper.execute(un, pw, email, fn, ln, url);
    }
}
