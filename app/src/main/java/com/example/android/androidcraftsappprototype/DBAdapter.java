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

    // To insert data to DB
    public long insertData(String chosenItem){
        SQLiteDatabase dbItem = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.RESULT, chosenItem);
        long id = dbItem.insert(DBHelper.RESULT_TABLE, null , contentValues);
        return id;
    }

    public String returnData(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selectQuery = "SELECT " + DBHelper.RESULT + " FROM "+ DBHelper.RESULT_TABLE;

        Cursor cursor=db.rawQuery(selectQuery, new String[] {});
        StringBuilder buffer = new StringBuilder();

        // Append every data together
        while (cursor.moveToNext())
        {
            //int cursorID = cursor.getInt(cursor.getColumnIndex(DBHelper.UID));
            String chosenItem = cursor.getString(cursor.getColumnIndex(DBHelper.RESULT));
            buffer.append(chosenItem + "/n");
        }
        return buffer.toString();

    }

    // To get data from DB by querying the items selected
    public String getData(int firstSelection, int secondSelection, int thirdSelection,
                          int fourthSelection, int fifthSelection)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String firstSelectionStr, secondSelectionStr, thirdSelectionStr, fourthSelectionStr, fifthSelectionStr;

        firstSelectionStr = Integer.toString(firstSelection);
        secondSelectionStr = Integer.toString(secondSelection);
        thirdSelectionStr = Integer.toString(thirdSelection);
        fourthSelectionStr = Integer.toString(fourthSelection);
        fifthSelectionStr = Integer.toString(fifthSelection);

        String[] columns = {DBHelper.UID,DBHelper.CNAME};
        //Cursor cursor = db.query(DBHelper.TABLE_NAME,columns,null,null,null,null,null);
        String selectQuery = "SELECT " + DBHelper.CNAME + " FROM "+ DBHelper.TABLE_NAME + " WHERE First_Attribute=? "
                + " AND Second_Attribute=? " + " AND Third_Attribute=? " + " AND Fourth_Attribute=? "
                + " AND Fifth_Attribute=? ";
        Cursor cursor=db.rawQuery(selectQuery, new String[] {firstSelectionStr, secondSelectionStr, thirdSelectionStr,
                            fourthSelectionStr, fifthSelectionStr});
        StringBuilder buffer = new StringBuilder();

        // Append every data together
        while (cursor.moveToNext())
        {
            //int cursorID = cursor.getInt(cursor.getColumnIndex(DBHelper.UID));
            String chosenItem = cursor.getString(cursor.getColumnIndex(DBHelper.CNAME));
            buffer.append(chosenItem + "/n");
        }
        return buffer.toString();
    }

    /*public String getResults(String firstSelection, String secondSelection, String thirdSelection,
                             String fourthSelection, String fifthSelection){

    }*/

    // To delete data in table
    public  int deleteData(String deleteItem)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] whereArgs ={deleteItem};

        int count = db.delete(DBHelper.TABLE_NAME ,DBHelper.CNAME+" = ?",whereArgs);
        return  count;
    }

    static class DBHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "CraftsAppDatabase.db";    // Database Name
        private static final String TABLE_NAME = "CraftTools";   // Table Name
        private static final String RESULT_TABLE = "Result";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String CNAME = "Craft_Name";    //Column II
        private static final String RESULT = "Result_Name";    //Column II
        private static final String FIRST_ATTRIBUTE = "First_Attribute";    //Column III
        private static final String SECOND_ATTRIBUTE = "Second_Attribute";    //Column IV
        private static final String THIRD_ATTRIBUTE = "Third_Attribute";    //Column V
        private static final String FOURTH_ATTRIBUTE = "Fourth_Attribute";    //Column VI
        private static final String FIFTH_ATTRIBUTE = "Fifth_Attribute";    //Column VII
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CNAME+" VARCHAR(255)" +
                ", "+FIRST_ATTRIBUTE+" VARCHAR(255), "+SECOND_ATTRIBUTE+" VARCHAR(255)" +
                ", "+THIRD_ATTRIBUTE+" VARCHAR(255), "+FOURTH_ATTRIBUTE+" VARCHAR(255)" +
                ", "+FIFTH_ATTRIBUTE+" VARCHAR(255));";
        private static final String CREATE_OTHER_TABLE = "CREATE TABLE "+RESULT_TABLE+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+RESULT+" VARCHAR(255));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+RESULT_TABLE;
        private Context context;

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        /*public void deleteTable(SQLiteDatabase db) {
            db = getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS CraftTools");
        }*/

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DROP_TABLE);
            db.execSQL(CREATE_OTHER_TABLE);
            db.execSQL(CREATE_TABLE);
            db.execSQL("INSERT INTO " + TABLE_NAME + "(Craft_Name, First_Attribute, Second_Attribute, Third_Attribute, Fourth_Attribute, Fifth_Attribute ) " +
                    "VALUES ('Landscape Drawing', '1', '4','8', '0', '0')");
            db.execSQL("INSERT INTO " + TABLE_NAME + "(Craft_Name, First_Attribute, Second_Attribute, Third_Attribute, Fourth_Attribute, Fifth_Attribute ) " +
                    "VALUES ('Popsicle Sticks House', '2', '3','0', '0', '0')");
            db.execSQL("INSERT INTO " + TABLE_NAME + "(Craft_Name, First_Attribute, Second_Attribute, Third_Attribute, Fourth_Attribute, Fifth_Attribute ) " +
                    "VALUES ('Sunset Painting', '4', '7','10', '0', '0')");

            /*try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }*/
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //db.execSQL(DROP_TABLE);
            onCreate(db);
            if (newVersion > oldVersion) {
                db.execSQL("ALTER TABLE CraftTools ADD COLUMN FIRST_ATTRIBUTE INTEGER DEFAULT 0");
                db.execSQL("ALTER TABLE CraftTools ADD COLUMN SECOND_ATTRIBUTE INTEGER DEFAULT 0");
                db.execSQL("ALTER TABLE CraftTools ADD COLUMN THIRD_ATTRIBUTE INTEGER DEFAULT 0");
                db.execSQL("ALTER TABLE CraftTools ADD COLUMN FOURTH_ATTRIBUTE INTEGER DEFAULT 0");
                db.execSQL("ALTER TABLE CraftTools ADD COLUMN FIFTH_ATTRIBUTE INTEGER DEFAULT 0");
            //onCreate(db);
            /*try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);*/
            }
        }


    }
}
