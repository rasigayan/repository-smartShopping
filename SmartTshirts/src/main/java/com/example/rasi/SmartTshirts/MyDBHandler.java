package com.example.rasi.SmartTshirts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tharindu on 14-Apr-15.
 */
public class MyDBHandler extends SQLiteOpenHelper {

    private static final String TAG = "tbsMessage";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "customer.db";
    private static final String TABLE_CUSTOMERS = "customers";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_MOBILE = "mobile";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_CUSTOMERS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USERNAME + " TEXT," + COLUMN_PASSWORD + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_MOBILE + " TEXT " + ");";
        db.execSQL(query);

        Log.i(TAG,"DB created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        onCreate(db);

        Log.i(TAG,"DB dropped");

    }

    public void addCustomer(CustomerDetails customerDetails){
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, customerDetails.get_userName());
        values.put(COLUMN_PASSWORD, customerDetails.get_password());
        values.put(COLUMN_EMAIL, customerDetails.get_email());
        values.put(COLUMN_MOBILE,customerDetails.get_mobile());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CUSTOMERS, null, values);
        db.close();

        Log.i(TAG,"User added");
    }

    public void deleteCustomer(String userName,String passWord) {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CUSTOMERS + " WHERE " + COLUMN_USERNAME + " =  \"" + userName + "\"" +" AND "+COLUMN_PASSWORD+" =  \"" + passWord + "\"");


    }




    public String databaseToString(String userName,String passWord) {

        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CUSTOMERS + " WHERE " + COLUMN_USERNAME + " =  \"" + userName + "\"" +" AND "+COLUMN_PASSWORD+" =  \"" + passWord + "\"";
        //cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);

        //move to the first row in your results
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("username")) != null) {
                dbString += c.getString(c.getColumnIndex("username"));
                dbString += "\n";
                dbString += c.getString(c.getColumnIndex("email"));
                dbString += "\n";
                dbString += c.getString(c.getColumnIndex("mobile"));
                dbString += "\n";
                Log.i(TAG,"Users printed");
            }
            c.moveToNext();
        }
        db.close();
        return dbString;


    }

    public CustomerDetails findCustomer(String userName,String password) {

        Log.i(TAG,"findCustomer method called");

        String query = "Select * FROM " + TABLE_CUSTOMERS + " WHERE " + COLUMN_USERNAME + " =  \"" + userName + "\"" +" AND "+COLUMN_PASSWORD+" =  \"" + password + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        CustomerDetails customerDetails = new CustomerDetails();

        Log.i(TAG," Customer details object created ");

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            customerDetails.set_id(Integer.parseInt(cursor.getString(0)));
            customerDetails.set_userName(cursor.getString(1));
            customerDetails.set_password(cursor.getString(2));
            customerDetails.set_email(cursor.getString(3));
            customerDetails.set_mobile(cursor.getString(4));
            cursor.close();
        } else {
            customerDetails = null;
        }
        db.close();
        return customerDetails;
    }
}
