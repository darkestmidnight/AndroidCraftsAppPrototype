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

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    Button regSbmtBtn;
    EditText regFirstN, regLastN, regUserN, regPass, regStreet, regCity, regState, regZip;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regSbmtBtn = (Button) findViewById(R.id.RegSubmitBtn);

        regFirstN = (EditText) findViewById(R.id.RegFirstName);
        regLastN = (EditText) findViewById(R.id.RegLastName);
        regUserN = (EditText) findViewById(R.id.RegUsername);
        regPass = (EditText) findViewById(R.id.RegPassword);
        regStreet = (EditText) findViewById(R.id.RegStreet);
        regCity = (EditText) findViewById(R.id.RegCity);
        regState = (EditText) findViewById(R.id.RegState);
        regZip = (EditText) findViewById(R.id.RegZipCode);

        regSbmtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // gets the input from the register textboxes
                String strFirstN = regFirstN.getText().toString();
                String strLastN = regLastN.getText().toString();
                String strUserN = regUserN.getText().toString();
                String strPass = regPass.getText().toString();
                String strStreet = regStreet.getText().toString();
                String strCity = regCity.getText().toString();
                String strState = regState.getText().toString();
                String strZip = regZip.getText().toString();


                // API url duh
                //String APIUrl = "http://192.168.0.18:8000/token-auth/";

                // If the user is authenticated, then transfer to the MainActivity page
                /*if (APIAuthentication(strUserName, strPassWord, APIUrl)){
                    startActivity(new Intent(Login.this, Posts.class));
                }*/

                /////////////// FIREBASE
                /*StringBuilder almostID = new StringBuilder();
                almostID.append(strFirstN + " " + strLastN);
                String theID = almostID.toString();

                Map<String, Object> docData = new HashMap<>();

                Map<String, Object> nameData = new HashMap<>();
                nameData.put("firstname", strFirstN);
                nameData.put("lastname", strLastN);

                Map<String, Object> credData = new HashMap<>();
                credData.put("username", strUserN);
                credData.put("password", strPass);

                Map<String, Object> addressData = new HashMap<>();
                addressData.put("street", strStreet);
                addressData.put("city", strCity);
                addressData.put("state", strState);
                addressData.put("zipCode", strZip);

                docData.put("name", nameData);
                docData.put("credentials", credData);
                docData.put("address", addressData);

                db.collection("Users").document(theID)
                        .set(docData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("TAG", "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error writing document", e);
                            }
                        });*/
            }
        });


    }
}
