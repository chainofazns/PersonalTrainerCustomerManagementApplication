package com.bignerdranch.android.personaltrainercustomermanagementapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Receipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.LogOff) {
            super.onOptionsItemSelected(item);
            Intent intent = new Intent(Receipt.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Logging Off", Toast.LENGTH_SHORT).show();
            return true;
        }
        return true;
    }
    public void printButton(View view) {
        //implement printer search function
        Intent intent = new Intent(Receipt.this, Receipt.class);
        startActivity(intent);
    }
    public void emailButton(View view) {
        //implement send to email function
        Intent intent = new Intent(Receipt.this, Receipt.class);
        startActivity(intent);
    }
    public void returnButton(View view) {
        //returns to sessions of the customer
        Intent intent = new Intent(Receipt.this, Sessions.class);
        startActivity(intent);
    }
}
