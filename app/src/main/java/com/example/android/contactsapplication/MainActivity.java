package com.example.android.contactsapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.database.sqlite.*;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.src;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    private ImageView photoIV;
    private EditText firstNameET;
    private EditText lastNameET;
    private String mCurrentPhotoPath;
    private Uri currentPhotoURI;
    private ContactsDataSource cds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photoIV = (ImageView) findViewById(R.id.photo_iv);
        firstNameET = (EditText) findViewById(R.id.firstname_et);
        lastNameET = (EditText) findViewById(R.id.lastname_et);
        cds = new ContactsDataSource(this);
        cds.open();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                InputStream image_stream = getContentResolver().openInputStream(currentPhotoURI);
                Bitmap bitmap = BitmapFactory.decodeStream(image_stream);
                Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                Bitmap scaledBitmap = resizedBitmap.createScaledBitmap(resizedBitmap, 230, 300, true);
                photoIV.setImageBitmap(scaledBitmap);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
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

    public void dispatchTakePictureIntent(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                currentPhotoURI = photoURI;
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }



    public void saveData(View view) {
        Contact con = new Contact();
        cds.createContact(firstNameET.getText().toString(),lastNameET.getText().toString(),currentPhotoURI.toString());
        Toast.makeText(this, "Contact saved successfully.", Toast.LENGTH_SHORT).show();
    }

    public void switchToList(View view) {
        Intent myIntent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(myIntent);
    }
}
