package com.example.exercise_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "finaldb.db";
    private static final int DATABASE_VERSION = 1;

    // Define the table and its columns
    private static final String TABLE_NAME = "exercise";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DATE_OF_BIRTH = "dateOfBirth";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_SELECTED_IMAGE_TAG = "selectedImageTag";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table with the specified columns
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DATE_OF_BIRTH + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_SELECTED_IMAGE_TAG + " TEXT);";

        db.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database schema upgrades here if needed
    }

    // Insert a new record into the table
    public long insertRecord(String name, String dateOfBirth, String email, String selectedImageTag) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DATE_OF_BIRTH, dateOfBirth);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_SELECTED_IMAGE_TAG, selectedImageTag);
        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

    // Query all records from the table
    public Cursor getAllRecords() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    // Update a record in the table
    public int updateRecord(long id, String name, String dateOfBirth, String email, String selectedImageTag) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DATE_OF_BIRTH, dateOfBirth);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_SELECTED_IMAGE_TAG, selectedImageTag);
        int rowsUpdated = db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rowsUpdated;
    }

    // Delete a record from the table
    public int deleteRecord(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rowsDeleted;
    }
}
