package com.example.locate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ContactManager";
    private static final String TABLE_EMPLOYEE = "employe";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    private static final String TABLE_MANAGER ="manager" ;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_EMPLOYEE_TABLE = "CREATE TABLE " + TABLE_EMPLOYEE + "("

                + KEY_PH_NO + " TEXT PRIMARY KEY,"
                + KEY_NAME + " TEXT"
                +")";
        db.execSQL(CREATE_EMPLOYEE_TABLE);
        //
        CREATE_EMPLOYEE_TABLE = "CREATE TABLE " + TABLE_MANAGER + "("

                + KEY_PH_NO + " TEXT PRIMARY KEY,"
                + KEY_NAME + " TEXT"
                +")";
        db.execSQL(CREATE_EMPLOYEE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);

        // Create tables again
       onCreate(db);
    }

    // code to add the new contact
    void addContact(Contact contact,String table_name) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone

       //0 contact is not verified yet

        // Inserting Row
        try{
           long k= db.insert(table_name, null, values);
             Log.d("d","d");}
        catch(Exception e){
           Log.d("exception",e.toString());
        }

        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    boolean searchContact(String phone) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MANAGER, new String[] {
                        KEY_NAME, KEY_PH_NO }, KEY_PH_NO + "=?",
                new String[] { String.valueOf(phone) }, null, null, null, null);
        if (cursor != null){
            if(cursor.getCount()>0){
                return true;
            }
            else return  false;
        }
            return  false;

        // return contact

    }

    // code to get all contacts in a list view
    public List<Contact> getAllContacts(String table_name) {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + table_name;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
               // if(cursor.getInt(2)==1) {//status is 1 ,verified
                    contact.setName(cursor.getString(1));
                    contact.setPhoneNumber(cursor.getString(0));
                    // Adding contact to list
                    contactList.add(contact);
                //}
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // code to update the single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact._name);
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(TABLE_EMPLOYEE, values, KEY_PH_NO + " = ?",
                new String[] { String.valueOf(contact.getPhoneNumber()) });
    }//

    //update manager
    public int updateContact_Manager(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact._name);
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(TABLE_MANAGER, values, KEY_PH_NO + " = ?",
                new String[] { String.valueOf(contact.getPhoneNumber()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact,String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, KEY_PH_NO+ " = ?",
                new String[] { String.valueOf(contact.getPhoneNumber()) });
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount() {
        int count;
        String countQuery = "SELECT  * FROM " + TABLE_EMPLOYEE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        count=cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

}