package com.example.android.androidcraftsappprototype;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.net.Uri;


public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    /*static {
        System.loadLibrary("native-lib");
    }*/

    ImageButton AboutBtn, HomeSettingsBtn;
    String url = "http://www.google.com";
    Button HomeBeginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        //TextView tv = (TextView) findViewById(R.id.sample_text);
        //tv.setText(stringFromJNI());

        // Button to go to google.com
        AboutBtn = (ImageButton) findViewById(R.id.HomePurposeBtn);
        AboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        // Button to go to the next page
        HomeBeginBtn = (Button) findViewById(R.id.HomeBeginBtn);
        HomeBeginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SetupPage.class));
            }
        });

        HomeSettingsBtn = (ImageButton) findViewById(R.id.HomeSettingsBtn);
        HomeSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Settings.class));
            }
        });
    }


    /*
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //public native String stringFromJNI();
}
