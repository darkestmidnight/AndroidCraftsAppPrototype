package com.example.android.androidcraftsappprototype;

import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.util.SparseBooleanArray;
import android.widget.Button;
import android.content.Intent;
import android.widget.ScrollView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SetupPage extends AppCompatActivity {

    // variables for home and setting up
    DBAdapter dbHelper;
    ImageButton SetupHomeBtn;
    //Button SetupAcptBtn;

    // variables for multiple selection
    ArrayAdapter<CharSequence> adapter;
    ListView ChoicesList;
    protected Button SetupAcceptBtn;
    protected CharSequence[] Choices = {
            "Nature", "Popsicle_Sticks", "Glue", "Paper", "Pencil", "Oil_Pastels", "Paint",
                "Charcoal", "Clay", "Sunset", "Ocean"};
    //protected ArrayList<CharSequence> SelectedOptions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new DBAdapter(this);

        // Deletes the database for new selection
        dbHelper.deleteDatabase(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_page);

        // to go back to Home page
        SetupHomeBtn = (ImageButton) findViewById(R.id.SetupHomeBtn);
        SetupHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetupPage.this, MainActivity.class));
            }
        });

        // To process the whole selection of categories for the app
        ChoicesList = (ListView) findViewById(R.id.SetupListView);
        // String[] of my array
        adapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_list_item_multiple_choice, Choices);

        // To save items selected
        ChoicesList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ChoicesList.setAdapter(adapter);

        // To go to the Begin page and store selected items in SQLite
        SetupAcceptBtn = (Button) findViewById(R.id.SetupAcceptBtn);
        SetupAcceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                int countSelected = ChoicesList.getCount();
                String[] selectedItems = new String[countSelected];
                SparseBooleanArray checkedItems = ChoicesList.getCheckedItemPositions();

                // Inserting chosen items to SQLite
                for (int i = 0; i < countSelected; i++){

                    // Every item are inserted to the Database
                    if(checkedItems.valueAt(i)){
                        selectedItems[i] = ChoicesList.getAdapter().getItem(checkedItems.keyAt(i)).toString();
                        dbHelper.insertData(selectedItems[i]);
                    }
                }

                // To go to next page after saving data to SQLite
                startActivity(new Intent(SetupPage.this, Begin.class));
            }
        });
    }
}
