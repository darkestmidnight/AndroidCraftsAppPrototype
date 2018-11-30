package com.example.android.androidcraftsappprototype;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class Settings extends AppCompatActivity implements View.OnClickListener{

    ImageButton AbstractBtn1, AbstractBtn2, AbstractBtn3;

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
        }

    // function to change the background of the app
    @Override
    public void onClick(View view) {
        ConstraintLayout CurrentLayout = (ConstraintLayout)findViewById(R.id.CurrentLayout);

        if (view.getId() == R.id.bckgroundbtn1) {
            CurrentLayout.setBackgroundResource(R.drawable.app_background);
        }
        else if (view.getId() == R.id.bckgroundbtn2) {
            CurrentLayout.setBackgroundResource(R.drawable.app_background2);
        }
        else if (view.getId() == R.id.bckgroundbtn3) {
            CurrentLayout.setBackgroundResource(R.drawable.app_background3);
        }
    }
}
