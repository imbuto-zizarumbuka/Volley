package com.example.crud_lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userManager";
    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_AVATAR_URL = "avatar_url";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_HTML_URL = "html_url";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEMORY_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_AVATAR_URL + " TEXT,"
                + KEY_HTML_URL + " TEXT,"
                + KEY_LOGIN + " TEXT"
                + ")";
        db.execSQL(CREATE_MEMORY_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new memory
    long addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AVATAR_URL, contact.getAvatar_url()); // Contact Name
        values.put(KEY_HTML_URL, contact.getHtml_url()); // Contact Name
        values.put(KEY_LOGIN,contact.getLogin());

        // Inserting Row
        long d= db.insert(TABLE_USERS, null, values);
        //2nd argument is String containing nullColumnHack

        db.close(); // Closing database connection
        return d;
    }

    // code to get the single contact
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID,
                        KEY_AVATAR_URL, KEY_LOGIN, KEY_HTML_URL}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        int mid=cursor.getInt(0);
        String avatar_url=cursor.getString(1);
        String login=cursor.getString(2);
        String html_url = cursor.getString(3);
        Contact contact = new Contact(avatar_url,login,html_url);
        contact.setId(mid);
        // return contact
        return contact;
    }

    // code to get all contacts in a list view
    public List<Contact> getAllContact() {
        List<Contact> contactsList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setAvatar_url(cursor.getString(1));
                contact.setLogin(cursor.getString(2));
                contact.setHtml_url(cursor.getString(3));
                // Adding memory to list
                contactsList.add(contact);
//                Log.d("Data: ",memory.getFirstname()+memory.getLastname()+memory.getTelephone());
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactsList;
    }

    // code to update the single contact
    public int updateMemory(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AVATAR_URL, contact.getAvatar_url());
        values.put(KEY_HTML_URL, contact.getHtml_url());
        values.put(KEY_LOGIN, contact.getLogin());

        // updating row
        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
        db.close();
    }

    // Getting contacts Count
    public int getContactCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}