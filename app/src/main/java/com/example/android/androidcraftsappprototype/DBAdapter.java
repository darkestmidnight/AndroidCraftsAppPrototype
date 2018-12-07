package com.example.android.androidcraftsappprototype;

//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DBAdapter{
    DBHelper dbHelper;

    // Default Constructor
    public DBAdapter(Context context){
        dbHelper = new DBHelper(context);
    }

    // To delete the Database
    public void deleteDatabase(Context context){
        context.deleteDatabase("CraftsAppDatabase");
    }

    // rename as emptyResultTable
    public void emptyResultTable(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Emptying RESULT_TABLE
        db.execSQL("DELETE FROM "+ dbHelper.RESULT_TABLE);
    }

    // To insert data to DB
    public void insertData(String chosenItem){
        SQLiteDatabase dbItem = dbHelper.getWritableDatabase();

        // Contentvalue to be used for DB insertion
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.RESULT, chosenItem);

        // Inserting chosenITem to the RESULT_TABLE
        dbItem.insert(DBHelper.RESULT_TABLE, null , contentValues);
    }

    public String returnData(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Gets result crafts from the CRAFT_TABLE stored in RESULT_TABLE
        String selectQuery = "SELECT " + DBHelper.RESULT + " FROM "+ DBHelper.RESULT_TABLE;

        // Stores the query results to a cursor
        Cursor cursor=db.rawQuery(selectQuery, new String[] {});
        StringBuilder buffer = new StringBuilder();

        // Append every data together
        while (cursor.moveToNext())
        {
            // To get string version of the result of the query
            String chosenItem = cursor.getString(cursor.getColumnIndex(DBHelper.RESULT));
            buffer.append(chosenItem + "");
        }
        cursor.close();
        return buffer.toString();

    }

    // To get data from DB by querying the items selected
    public String getData(int firstSelection, int secondSelection, int thirdSelection,
                          int fourthSelection, int fifthSelection)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String firstSelectionStr, secondSelectionStr, thirdSelectionStr, fourthSelectionStr, fifthSelectionStr;

        // To convert integer parameters to string for DB query
        firstSelectionStr = Integer.toString(firstSelection);
        secondSelectionStr = Integer.toString(secondSelection);
        thirdSelectionStr = Integer.toString(thirdSelection);
        fourthSelectionStr = Integer.toString(fourthSelection);
        fifthSelectionStr = Integer.toString(fifthSelection);

        // DB Query statement
        String selectQuery = "SELECT * FROM "+ DBHelper.CRAFT_TABLE + " WHERE " + DBHelper.FIRST_ATTRIBUTE + "=? "
                + " AND " + DBHelper.SECOND_ATTRIBUTE + "=? " + " AND " + DBHelper.THIRD_ATTRIBUTE + "=? " + " AND " + DBHelper.FOURTH_ATTRIBUTE + "=? "
                + " AND " + DBHelper.FIFTH_ATTRIBUTE + "=?";

        // Stores the string to cursor for rawQuery use
        Cursor cursor=db.rawQuery(selectQuery, new String[] {firstSelectionStr, secondSelectionStr, thirdSelectionStr,
                            fourthSelectionStr, fifthSelectionStr});

        // To store output on a buffer
        StringBuilder buffer = new StringBuilder();

        // If cursor receives return from query and not empty
        if (cursor != null && cursor.getCount() > 0) {
            // Move to the first row of the query
            cursor.moveToFirst();
            // Append every data together
            do {
                String chosenItem = cursor.getString(cursor.getColumnIndex(DBHelper.CNAME));
                buffer.append(chosenItem + " ");
            } while (cursor.moveToNext());
        }
        cursor.close();

        return buffer.toString();
    }

    // To delete data in table
    public  int deleteData(String deleteItem)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] whereArgs ={deleteItem};

        int count = db.delete(DBHelper.CRAFT_TABLE ,DBHelper.CNAME+" = ?",whereArgs);
        return  count;
    }

    // Section of database and table updates and creation
    class DBHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "CraftsAppDatabase.db";    // Database Name
        private static final String CRAFT_TABLE = "CraftTools";   // Table Name
        private static final String RESULT_TABLE = "Result";   // Table Name
        private static final int DATABASE_Version = 4;    // Database Version

        private static final String RESULT = "Result_Name";    //Result Column for RESULT_TABLE

        private static final String UID="_id";     // Column I (Primary Key)
        private static final String CNAME = "Craft_Name";    //Column II
        private static final String FIRST_ATTRIBUTE = "First_Attribute";    //Column III
        private static final String SECOND_ATTRIBUTE = "Second_Attribute";    //Column IV
        private static final String THIRD_ATTRIBUTE = "Third_Attribute";    //Column V
        private static final String FOURTH_ATTRIBUTE = "Fourth_Attribute";    //Column VI
        private static final String FIFTH_ATTRIBUTE = "Fifth_Attribute";    //Column VII

        // Statement to create the CRAFT_TABLE
        private static final String CREATE_CRAFTS_TABLE = "CREATE TABLE "+CRAFT_TABLE+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CNAME+" INTEGER DEFAULT 0" +
                ", "+FIRST_ATTRIBUTE+" INTEGER DEFAULT 0, "+SECOND_ATTRIBUTE+" INTEGER DEFAULT 0" +
                ", "+THIRD_ATTRIBUTE+" INTEGER DEFAULT 0, "+FOURTH_ATTRIBUTE+" INTEGER DEFAULT 0" +
                ", "+FIFTH_ATTRIBUTE+" INTEGER DEFAULT 0);";

        // Statement to create RESULT_TABLE
        private static final String CREATE_RESULT_TABLE = "CREATE TABLE "+RESULT_TABLE+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+RESULT+" VARCHAR(255));";

        //private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+RESULT_TABLE;

        // Statement to drop CRAFTS_TABLE at creation or DB update
        private static final String DROP_CRAFTS_TABLE ="DROP TABLE IF EXISTS "+CRAFT_TABLE;
        private static final String DROP_RESULT_TABLE ="DROP TABLE IF EXISTS "+RESULT_TABLE;
        private Context context;

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {
            //db.execSQL(DROP_TABLE);
            db.execSQL(DROP_CRAFTS_TABLE);
            db.execSQL(DROP_RESULT_TABLE);
            db.execSQL(CREATE_RESULT_TABLE);
            db.execSQL(CREATE_CRAFTS_TABLE);
            db.execSQL("INSERT INTO " + CRAFT_TABLE + "(Craft_Name, First_Attribute, Second_Attribute, Third_Attribute, Fourth_Attribute, Fifth_Attribute ) " +
                    "VALUES ('Landscape Drawing', 1, 4, 8, 0, 0)");
            db.execSQL("INSERT INTO " + CRAFT_TABLE + "(Craft_Name, First_Attribute, Second_Attribute, Third_Attribute, Fourth_Attribute, Fifth_Attribute ) " +
                    "VALUES ('Popsicle Sticks House', 2, 3, 0, 0, 0)");
            db.execSQL("INSERT INTO " + CRAFT_TABLE + "(Craft_Name, First_Attribute, Second_Attribute, Third_Attribute, Fourth_Attribute, Fifth_Attribute ) " +
                    "VALUES ('Sunset Painting', 4, 7, 10, 0, 0)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //db.execSQL(DROP_TABLE);
            onCreate(db);
            }
    }
}
