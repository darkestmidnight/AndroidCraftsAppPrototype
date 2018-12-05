package com.example.android.androidcraftsappprototype;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class Settings extends AppCompatActivity implements View.OnClickListener{

    ImageButton AbstractBtn1, AbstractBtn2, AbstractBtn3;
    SharedPreferences ShPreference;
    SharedPreferences.Editor PrefEditor;
    static String MyPREFERENCES = "MyPrefs";
    String BackgroundChoice = "BckgrndChoice";
    ConstraintLayout CurrentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        AbstractBtn1 = (ImageButton) findViewById(R.id.bckgroundbtn1);
        AbstractBtn2 = (ImageButton) findViewById(R.id.bckgroundbtn2);
        AbstractBtn3 = (ImageButton) findViewById(R.id.bckgroundbtn3);
        AbstractBtn1.setOnClickListener(this);
        AbstractBtn2.setOnClickListener(this);
        AbstractBtn3.setOnClickListener(this);
        CurrentLayout = (ConstraintLayout)findViewById(R.id.CurrentLayout);
        ShPreference = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        // sets the background according to the shared preference
        if (ShPreference.getInt(BackgroundChoice, -1) == 1){
            CurrentLayout.setBackgroundResource(R.drawable.app_background);
        }
        else if (ShPreference.getInt(BackgroundChoice, -1) == 2){
            CurrentLayout.setBackgroundResource(R.drawable.app_background2);
        }
        else if (ShPreference.getInt(BackgroundChoice, -1) == 3){
            CurrentLayout.setBackgroundResource(R.drawable.app_background3);
        }
    }

    // function to change the background of the app
    @Override
    public void onClick(View view) {
        PrefEditor = ShPreference.edit();

        if (view.getId() == R.id.bckgroundbtn1) {
            // to save the preference chosen to sharedpreference
            PrefEditor.putInt(BackgroundChoice, 1);
            PrefEditor.commit();
            // to set the background immediately when choosen from the settings
            CurrentLayout.setBackgroundResource(R.drawable.app_background);
        }
        else if (view.getId() == R.id.bckgroundbtn2) {
            // to save the preference chosen to sharedpreference
            PrefEditor.putInt(BackgroundChoice, 2);
            PrefEditor.commit();
            // to set the background immediately when choosen from the settings
            CurrentLayout.setBackgroundResource(R.drawable.app_background2);
        }
        else if (view.getId() == R.id.bckgroundbtn3) {
            // to save the preference chosen to sharedpreference
            PrefEditor.putInt(BackgroundChoice, 3);
            PrefEditor.commit();
            // to set the background immediately when choosen from the settings
            CurrentLayout.setBackgroundResource(R.drawable.app_background3);
        }
    }
}
