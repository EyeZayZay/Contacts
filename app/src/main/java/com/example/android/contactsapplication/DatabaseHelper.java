package com.example.android.contactsapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static java.sql.Types.NULL;

/**
 * Created by Android on 5/30/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String dbName="contactDB";
    static final int dbVersion = 1;
    static final String contactsTable="Contacts";
    static final String coLID = "ContactsID";
    static final String colFName="FirstName";
    static final String colLName="LastName";
    static final String colPhoto="Photo";

    public DatabaseHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + contactsTable + "(" + coLID + "INTEGER PRIMARY KEY AUTOINCREMENT, "+
        colFName +" TEXT, "+colLName+" TEXT, "+colPhoto+" TEXT)");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + contactsTable);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
    }
}
