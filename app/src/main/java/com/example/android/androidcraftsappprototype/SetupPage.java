package com.example.android.androidcraftsappprototype;

import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.content.Intent;
import android.widget.ScrollView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SetupPage extends AppCompatActivity {

    // variables for home and setting up
    ImageButton SetupHomeBtn;
    //Button SetupAcptBtn;

    // variables for multiple selection
    ArrayAdapter<CharSequence> adapter;
    ListView ListView1;
    protected Button SetupAcceptBtn;
    protected CharSequence[] receivers = {
            "Receiver1", "Receiver2", "Receivers3"};
    //protected ArrayList<CharSequence> SelectedOptions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_page);

        // to go back one page
        SetupHomeBtn = (ImageButton) findViewById(R.id.SetupHomeBtn);
        SetupHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetupPage.this, MainActivity.class));
            }
        });

        // to process the whole selection of categories for the app thing
        ListView1 = (ListView) findViewById(R.id.SetupListView);
        // string[] of my array
        adapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_list_item_multiple_choice, receivers);

        ListView1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ListView1.setAdapter(adapter);

        SetupAcceptBtn = (Button) findViewById(R.id.SetupAcceptBtn);
        //SetupAcceptBtn.setOnClickListener((View.OnClickListener) this);
        /*SetupAcceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(SetupPage.this, MainActivity.class));
                String selected;

            }
        });*/

        //scrollView1.setChoiceMode - disregard

        // To go to the next page
        //SetupAcptBtn = (Button) findViewById(R.id.SetupAcceptBtn);
        SetupAcceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetupPage.this, Begin.class));
            }
        });
    }

    //@Override
    /*public void onClick(View view) {
        switch (view.getId()) {
            case R.id.SetupAcceptBtn:
                showSetupAcceptDialog();
                break;
            default:
                break;
        }
    }

    protected void onChangeSelectedOptions() {
        StringBuilder stringBuilder = new StringBuilder();
        for (CharSequence receivers : SelectedOptions) {
            stringBuilder.append(receivers + ",");
        }
        adapter.notifyDataSetChanged();
    }

    protected void showSetupAcceptDialog() {
        boolean[] checkedOptions = new boolean[receivers.length];
        int count = receivers.length;

        for (int i = 0; i < count; i++) {
            checkedOptions[i] = SelectedOptions.contains(receivers[i]);
        }

        DialogInterface.OnMultiChoiceClickListener OptionsDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked)
                    SelectedOptions.add(receivers[which]);
                else
                    SelectedOptions.remove(receivers[which]);

                onChangeSelectedOptions();
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Options");
        builder.setMultiChoiceItems(receivers, checkedOptions, OptionsDialogListener);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }*/
}
