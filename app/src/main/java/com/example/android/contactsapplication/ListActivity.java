package com.example.android.contactsapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    public void switchToMain(View view) {
        Intent myIntent = new Intent(ListActivity.this, MainActivity.class);
        startActivity(myIntent);
    }
}
