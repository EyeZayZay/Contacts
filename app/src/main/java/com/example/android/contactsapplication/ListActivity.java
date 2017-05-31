package com.example.android.contactsapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ContactsDataSource cds;
    private TextView listcontactTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        cds = new ContactsDataSource(this);
        cds.open();
        listcontactTV = (TextView) findViewById(R.id.listcontact_tv);
        listContacts();
    }

    public void switchToMain(View view) {
        Intent myIntent = new Intent(ListActivity.this, MainActivity.class);
        startActivity(myIntent);
    }

    public void listContacts() {
        List<Contact> contacts = cds.getAllContacts();
        for (Contact con: contacts) {
            listcontactTV.append(con.getfirstName()+" ");
            listcontactTV.append(con.getlastName()+" ");
            listcontactTV.append(con.getPhoto()+"\n");
        }
    }
}
