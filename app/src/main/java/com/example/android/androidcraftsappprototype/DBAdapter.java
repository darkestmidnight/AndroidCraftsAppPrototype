package com.example.android.androidcraftsappprototype;

//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;

public class DBAdapter{
    DBHelper dbHelper;

    // Default Constructor
    public DBAdapter(Context context){
        dbHelper = new DBHelper(context);
    }

    public long insertData(String chosenItem){
        SQLiteDatabase dbItem = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NAME, chosenItem);
        long id = dbItem.insert(DBHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public String getData()
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {DBHelper.UID,DBHelper.NAME};
        Cursor cursor =db.query(DBHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuilder buffer= new StringBuilder();
        while (cursor.moveToNext())
        {
            int cursorID = cursor.getInt(cursor.getColumnIndex(DBHelper.UID));
            String chosenItem = cursor.getString(cursor.getColumnIndex(DBHelper.NAME));
            buffer.append(cursorID+ "   " + chosenItem + "   " +" \n");
        }
        return buffer.toString();
    }

    public  int deleteData(String deleteItem)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] whereArgs ={deleteItem};

        int count =db.delete(DBHelper.TABLE_NAME ,DBHelper.NAME+" = ?",whereArgs);
        return  count;
    }

    static class DBHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "CraftsAppDatabase";    // Database Name
        private static final String TABLE_NAME = "CraftTools";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String NAME = "ChosenItems";    //Column II
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
            /*try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }*/
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE);
            onCreate(db);
            /*try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }*/
        }


    }
}
