package com.bignerdranch.android.personaltrainercustomermanagementapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class NewCustomer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.LogOff) {
            super.onOptionsItemSelected(item);
            Intent intent = new Intent (NewCustomer.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Logging Off", Toast.LENGTH_SHORT).show();
            return true;
        }
        return true;
    }

    public void takePictureButton(View view) {
        //set to use camera when we learn how to do
        Intent intent = new Intent(NewCustomer.this, NewCustomer.class);
        startActivity(intent);
    }

    public void createCustomerButton(View view) {
        //links to new customer's session
        Intent intent = new Intent(NewCustomer.this, Sessions.class);
        startActivity(intent);
    }

}
