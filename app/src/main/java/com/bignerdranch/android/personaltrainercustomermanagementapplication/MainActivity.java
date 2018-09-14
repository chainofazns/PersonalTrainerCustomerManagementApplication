package com.bignerdranch.android.personaltrainercustomermanagementapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.bignerdranch.android.personaltrainercustomermanagementapplication.PTCMDatabase.KEY_FIRST_NAME;
import static com.bignerdranch.android.personaltrainercustomermanagementapplication.PTCMDatabase.KEY_LAST_NAME;
import static com.bignerdranch.android.personaltrainercustomermanagementapplication.PTCMDatabase.KEY_SESSIONS;

public class MainActivity extends AppCompatActivity{


    private PTCMDatabase mDatabase = new PTCMDatabase(MainActivity.this);
    private TextView cust1Name;
    private TextView cust1Progress;
    private TextView cust2Name;
    private TextView cust2Progress;
    private TextView cust3Name;
    private TextView cust3Progress;
    private TextView cust4Name;
    private TextView cust4Progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cursor cursor =  mDatabase.getCustomer();

        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            cust1Name = findViewById(R.id.customerName1);
            cust1Progress = findViewById(R.id.customerProgress1);
            cust1Name.setText(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)) +  " " + cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
            cust1Progress.setText(cursor.getString(cursor.getColumnIndex(KEY_SESSIONS)));

            //this is where i realize i made a mistake but its too late for me to not commit
            cursor.moveToNext();
            if (cursor != null && cursor.getCount() > 1){
                cust2Name = findViewById(R.id.customerName2);
                cust2Progress = findViewById(R.id.customerProgress2);
                cust2Name.setText(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)) + " " + cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
                cust2Progress.setText(cursor.getString(cursor.getColumnIndex(KEY_SESSIONS)));

                cursor.moveToNext();
                if (cursor != null && cursor.getCount() > 2){
                    cust3Name = findViewById(R.id.customerName3);
                    cust3Progress = findViewById(R.id.customerProgress3);
                    cust3Name.setText(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)) + " " + cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
                    cust3Progress.setText(cursor.getString(cursor.getColumnIndex(KEY_SESSIONS)));

                    cursor.moveToNext();
                    if (cursor != null && cursor.getCount() > 3){
                        cust4Name = findViewById(R.id.customerName4);
                        cust4Progress = findViewById(R.id.customerProgress4);
                        cust4Name.setText(cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME)) + " " + cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME)));
                        cust4Progress.setText(cursor.getString(cursor.getColumnIndex(KEY_SESSIONS)));
                    }
                }
            }
            cursor.close();
        }

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
            Intent intent = new Intent (MainActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Logging Off", Toast.LENGTH_SHORT).show();
            return true;
        }
        return true;
    }

    public void newCustomerButton(View view) {
        Intent intent = new Intent(MainActivity.this, NewCustomer.class);
        startActivity(intent);
    }

    public void customerSession(View view) {
        //implement switch to decide what data to display per profile
        Intent intent = new Intent(MainActivity.this, Sessions.class);
        startActivity(intent);
    }

}
