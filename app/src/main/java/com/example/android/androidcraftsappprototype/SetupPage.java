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
            "1. Nature", "2. Popsicle_Sticks", "3. Glue", "4. Paper", "5. Pencil", "6. Oil_Pastels", "7. Paint",
                "8. Charcoal", "9. Clay", "10. Sunset", "11. Ocean"};
    //protected ArrayList<CharSequence> SelectedOptions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new DBAdapter(this);

        // Deletes the database for new selection
        //dbHelper.deleteDatabase(this);

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
                //int countSelected = ChoicesList.getCount();
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
                        //dbHelper.insertData(selectedItems[i]);
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
               // int countedItems = strToIntItems.length;
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

                String queryResult = dbHelper.getData(strToIntItems[0], strToIntItems[1], strToIntItems[2], strToIntItems[3], strToIntItems[4]);
                dbHelper.insertData(queryResult);

                // To go to next page after saving data to SQLite
                startActivity(new Intent(SetupPage.this, Begin.class));
            }
        });
    }
}
