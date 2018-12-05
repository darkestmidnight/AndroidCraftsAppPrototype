package com.example.android.androidcraftsappprototype;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

public class Begin extends AppCompatActivity {

    DBAdapter dbHelper;
    ImageButton BeginHomeBtn;
    Button BeginDoneBtn;
    TextView OutputTxt;

    // variables for sharedpreference
    SharedPreferences ShPreference;
    SharedPreferences.Editor PrefEditor;
    static String MyPREFERENCES = "MyPrefs";
    String BackgroundChoice = "BckgrndChoice";
    ConstraintLayout BeginLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        BeginLayout = (ConstraintLayout)findViewById(R.id.BeginLayout);
        ShPreference = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        // sets the background according to the shared preference
        if (ShPreference.getInt(BackgroundChoice, -1) == 1){
            BeginLayout.setBackgroundResource(R.drawable.app_background);
        }
        else if (ShPreference.getInt(BackgroundChoice, -1) == 2){
            BeginLayout.setBackgroundResource(R.drawable.app_background2);
        }
        else if (ShPreference.getInt(BackgroundChoice, -1) == 3){
            BeginLayout.setBackgroundResource(R.drawable.app_background3);
        }

        dbHelper = new DBAdapter(this);

        // To go back to the Home page
        BeginHomeBtn = (ImageButton) findViewById(R.id.BeginHomeBtn);
        BeginHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Begin.this, SetupPage.class));
            }
        });

        // To set the text of the page according to the DB table content
        OutputTxt = (TextView) findViewById(R.id.BeginOutputTxt);
        String data = dbHelper.returnData();
        OutputTxt.setText(data);

        // To go back to Home page when done
        BeginDoneBtn = (Button) findViewById(R.id.BeginDoneBtn);
        BeginDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Begin.this, MainActivity.class));
            }
        });
    }

    /*public void viewItems(View view){
        String data = dbHelper.getData();
        OutputTxt.setText(data);
    }*/
}
