package com.example.android.androidcraftsappprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class Begin extends AppCompatActivity {

    ImageButton BeginHomeBtn;
    Button BeginDoneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        BeginHomeBtn = (ImageButton) findViewById(R.id.BeginHomeBtn);
        BeginHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Begin.this, SetupPage.class));
            }
        });

        BeginDoneBtn = (Button) findViewById(R.id.BeginDoneBtn);
        BeginDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Begin.this, MainActivity.class));
            }
        });
    }
}
