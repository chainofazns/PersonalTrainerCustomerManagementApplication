package com.bignerdranch.android.personaltrainercustomermanagementapplication;



import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NewCustomer extends AppCompatActivity {

    private PTCMDatabase mDatabase = new PTCMDatabase(NewCustomer.this);
    private ImageView mImageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);
        mImageView = (ImageView) findViewById(R.id.imageView);

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
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            try{
                photoFile = createImageFile();
            } catch (IOException ex) {
                //error
                System.out.println(ex);
            }
            if (photoFile!= null){
                //Uri photoURI = FileProvider.getUriForFile(this, "com.bignerdranch.android.fileprovider", photoFile);
                Uri photoURI = Uri.fromFile(photoFile);
                //intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
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
