package com.example.android.androidcraftsappprototype;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
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

    // Variables for general buttons of this activity
    ImageButton AboutBtn, HomeSettingsBtn;
    String url = "http://www.google.com";
    Button HomeBeginBtn, HomeLoadBtn;

    // variables for sharedpreference
    SharedPreferences ShPreference;
    SharedPreferences.Editor PrefEditor;
    static String MyPREFERENCES = "MyPrefs";
    String BackgroundChoice = "BckgrndChoice";
    ConstraintLayout MainMenuLayout;

    @Override
    protected void onResume(){
        super.onResume();

        // Tells MainMenuLayout id of the activity background
        MainMenuLayout = (ConstraintLayout)findViewById(R.id.main_menu_layout);

        // gets the saved SharedPreferences values
        ShPreference = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        // sets the background according to the shared preference
        if (ShPreference.getInt(BackgroundChoice, -1) == 1){
            MainMenuLayout.setBackgroundResource(R.drawable.app_background);
        }
        else if (ShPreference.getInt(BackgroundChoice, -1) == 2){
            MainMenuLayout.setBackgroundResource(R.drawable.app_background2);
        }
        else if (ShPreference.getInt(BackgroundChoice, -1) == 3){
            MainMenuLayout.setBackgroundResource(R.drawable.app_background3);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        HomeLoadBtn = (Button) findViewById(R.id.HomeLoadBtn);
        HomeLoadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Login.class));
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
