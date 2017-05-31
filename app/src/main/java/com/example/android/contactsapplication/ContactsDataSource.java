package com.example.android.contactsapplication;

/**
 * Created by Android on 5/30/2017.
 */
import java.util.ArrayList;
        import java.util.List;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;

public class ContactsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {DatabaseHelper.colFName, DatabaseHelper.colLName, DatabaseHelper.colPhoto};

    public ContactsDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Contact createContact(String firstName, String lastName, String photo) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.colFName, firstName);
        values.put(DatabaseHelper.colLName, lastName);
        values.put(DatabaseHelper.colPhoto, photo);
        long insertId = database.insert(DatabaseHelper.contactsTable, null,
                values);
        Cursor cursor = database.query(DatabaseHelper.contactsTable,
                allColumns, DatabaseHelper.coLID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Contact newContact = cursorToContact(cursor);
        cursor.close();
        return newContact;
    }

    public List<Contact> getAllContacts() {
        List<Contact> Contacts = new ArrayList<Contact>();

        Cursor cursor = database.query(DatabaseHelper.contactsTable,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Contact Contact = cursorToContact(cursor);
            Contacts.add(Contact);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return Contacts;
    }

    private Contact cursorToContact(Cursor cursor) {
        Contact Contact = new Contact();
        Contact.setId(cursor.getLong(0));
        Contact.setfirstName(cursor.getString(0));
        Contact.setlastName(cursor.getString(1));
        Contact.setPhoto(cursor.getString(2));
        return Contact;
    }
}
