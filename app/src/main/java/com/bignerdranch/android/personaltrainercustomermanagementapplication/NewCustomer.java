package com.bignerdranch.android.personaltrainercustomermanagementapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.bignerdranch.android.personaltrainercustomermanagementapplication.PTCMDatabase.KEY_FIRST_NAME;
import static com.bignerdranch.android.personaltrainercustomermanagementapplication.PTCMDatabase.KEY_LAST_NAME;

public class NewCustomer extends AppCompatActivity {

    private PTCMDatabase mDatabase = new PTCMDatabase(NewCustomer.this);


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



        EditText eFname =  findViewById(R.id.custFirst);
        EditText eLname =  findViewById(R.id.custLast);
        EditText eDob =  findViewById(R.id.custDOB);
        EditText eSession = findViewById(R.id.custSessions);
        EditText eCard =  findViewById(R.id.custCard);
        EditText eMonth =  findViewById(R.id.custCardMonth);
        EditText eYear = findViewById(R.id.custCardYear);
        EditText eCvc =  findViewById(R.id.custSecurity);
        EditText eAdd1 =  findViewById(R.id.custAddress1);
        EditText eAdd2 =  findViewById(R.id.custAddress2);
        EditText eCity =  findViewById(R.id.custCity);
        EditText eState =  findViewById(R.id.custState);
        EditText eZip = findViewById(R.id.custZip);

        mDatabase.addNewCustomer(eFname.getText().toString(),
                eLname.getText().toString(),
                eDob.getText().toString(),
                Integer.parseInt(eSession.getText().toString()),
                eCard.getText().toString(),
                Integer.parseInt(eMonth.getText().toString()),
                Integer.parseInt(eYear.getText().toString()),
                Integer.parseInt(eCvc.getText().toString()),
                eAdd1.getText().toString(),
                eAdd2.getText().toString(),
                eCity.getText().toString(),
                eState.getText().toString(),
                eZip.getText().toString());
        Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(NewCustomer.this, MainActivity.class);
        startActivity(intent);
    }

}
