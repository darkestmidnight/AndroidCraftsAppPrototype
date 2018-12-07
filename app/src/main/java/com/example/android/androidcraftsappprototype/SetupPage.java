package com.example.android.androidcraftsappprototype;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.util.SparseBooleanArray;
import android.widget.Button;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SetupPage extends AppCompatActivity {

    // variables for home and setting up
    DBAdapter dbHelper;
    ImageButton SetupHomeBtn;

    // variables for sharedpreference
    SharedPreferences ShPreference;
    SharedPreferences.Editor PrefEditor;
    static String MyPREFERENCES = "MyPrefs";
    String BackgroundChoice = "BckgrndChoice";
    ConstraintLayout SetupPageLayout;

    // variables for multiple selection
    ArrayAdapter<CharSequence> adapter;
    ListView ChoicesList;
    protected Button SetupAcceptBtn;
    protected CharSequence[] Choices = {
            "1. Nature", "2. Popsicle_Sticks", "3. Glue", "4. Paper", "5. Pencil", "6. Oil_Pastels", "7. Paint",
                "8. Charcoal", "9. Clay", "10. Sunset", "11. Ocean"};

    @Override
    protected void onResume(){
        super.onResume();

        dbHelper = new DBAdapter(this);

        // to empty result table
        dbHelper.emptyResultTable();

        // Tells SetupPageLayout id of the activity background
        SetupPageLayout = (ConstraintLayout)findViewById(R.id.SetupPageLayout);

        // gets the saved SharedPreferences values
        ShPreference = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        // sets the background according to the shared preference
        if (ShPreference.getInt(BackgroundChoice, -1) == 1){
            SetupPageLayout.setBackgroundResource(R.drawable.app_background);
        }
        else if (ShPreference.getInt(BackgroundChoice, -1) == 2){
            SetupPageLayout.setBackgroundResource(R.drawable.app_background2);
        }
        else if (ShPreference.getInt(BackgroundChoice, -1) == 3){
            SetupPageLayout.setBackgroundResource(R.drawable.app_background3);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_page);

        dbHelper = new DBAdapter(this);

        // To go back to Home page
        SetupHomeBtn = (ImageButton) findViewById(R.id.SetupHomeBtn);
        SetupHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetupPage.this, MainActivity.class));
            }
        });

        // To process the whole selection of categories for the app
        ChoicesList = (ListView) findViewById(R.id.SetupListView);
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

                int countSelected = 5;
                String[] selectedItems = new String[countSelected];

                SparseBooleanArray checkedItems = ChoicesList.getCheckedItemPositions();

                // Default values of selectedItems variable
                for (int i = 0; i < countSelected; i++){
                    selectedItems[i] = "0. Default";
                }

                // Inserting chosen items to SQLite
                for (int i = 0; i < countSelected; i++){

                    // Every item are inserted to the Database
                    if(checkedItems.valueAt(i)){
                        selectedItems[i] = ChoicesList.getAdapter().getItem(checkedItems.keyAt(i)).toString();
                    }
                }

                // algorithm to convert the choices to a single numerical value
                int[] strToIntItems = new int[countSelected];
                int countedItems = strToIntItems.length;

                for (int i = 0; i < countSelected; i++) {
                    // removes all non numerical values from the string
                    selectedItems[i] = selectedItems[i].replaceAll("\\D+","");
                    // converts the 1 digit string to an int
                    strToIntItems[i] += Integer.parseInt(selectedItems[i]);
                }

                // sorting algorithm for the attributes.
                for (int i = 0; i < countedItems; i++) // bubble sort outer loop
                {
                    for (int j = 0; j < countedItems - i - 1; i++) {
                        if (strToIntItems[j] > strToIntItems[j+1])
                        {
                            // swap temp to strToIntItems[j]
                            int temp = strToIntItems[j];
                            strToIntItems[j] = strToIntItems[j+1];
                            strToIntItems[j+1] = temp;
                        }
                    }
                }

                // Gets data from the CRAFT_TABLE
                String queryResult = dbHelper.getData(strToIntItems[0], strToIntItems[1], strToIntItems[2], strToIntItems[3], strToIntItems[4]);

                // Inserting returns from CRAFT_TABLE to RESULT_TABLE
                dbHelper.insertData(queryResult);

                // To go to next page after saving data to SQLite
                startActivity(new Intent(SetupPage.this, Begin.class));
            }
        });
    }
}
