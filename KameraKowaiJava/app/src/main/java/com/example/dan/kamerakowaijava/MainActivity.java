package com.example.dan.kamerakowaijava;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.support.v4.content.FileProvider.getUriForFile;

public class MainActivity extends AppCompatActivity {
    public static final int CAMERA_PERMISSION_REQUEST = 1995;
    private Button btnCamera;
    private Button btnGallery;
    private Button btnUpload;
    private Button btnUploadBase64;
    private ImageView ivThumbnails;
    String mCurrentPhotoPath;
    String part_image;
    ProgressDialog progressDialog;
    private static final int REQUEST_TAKE_PHOTO = 188;
    private static final int REQUEST_GALLERY = 189;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = getUriForFile(this,
                        BuildConfig.APPLICATION_ID+".provider",
                        photoFile);
                System.out.println(photoURI);
                System.out.println(mCurrentPhotoPath);
                File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                System.out.println("storageDir : "+storageDir.getAbsolutePath());

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "KameraKowaiJava" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File buatan = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        System.out.println("STORAGEDIR : "+storageDir.getAbsolutePath());
        System.out.println("BUATAN : "+buatan.getAbsolutePath());

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_PERMISSION_REQUEST){
            //we are hearing from the camera
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                dispatchTakePictureIntent();
            }else{
                Toast.makeText(this, "Unable to invoke camera without permission",Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void invokeCamera(){
        //path to the images directory
        File imagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        //create imagefile name
        String picture = createPictureName();

        //create an imagefile at this path.
        File imageFile = new File(imagePath, picture);

        //convert file to URI
        Uri pictureUri = getUriForFile(MainActivity.this, BuildConfig.APPLICATION_ID+".provider", imageFile);
//        Uri pictureUri = Uri.fromFile(imageFile);

        //create the intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

//        Uri uri = FileProvider.getUriForFile(this,getApplicationContext().getPackageName()+".provider", picFile);
        //where do I want to save the image?
//        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //passPermission to the camera
//        takePictureIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//                Toast.makeText(getApplicationContext(), "Error while saving picture.", Toast.LENGTH_LONG).show();
//                ex.printStackTrace();
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                //                Uri photoURI = FileProvider.getUriForFile(this,
//                //                        "com.example.dan.kamerakowaijava.provider",
//                //                        photoFile);
//                //                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//            }
//        }
    }

    private String createPictureName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        return "KameraKowaiJava"+timestamp+".jpg";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnCamera = (Button) findViewById(R.id.btn_take_photo);
        btnGallery = (Button) findViewById(R.id.btn_choose_gallery);
        btnUpload = (Button) findViewById(R.id.btn_upload_1);
        btnUploadBase64 = (Button) findViewById(R.id.btn_upload_2);
        ivThumbnails = (ImageView) findViewById(R.id.iv_thumb);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        btnUploadBase64.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, FutureStudActivity.class);
                startActivity(in);
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                    galleryIntent.setType("image/*");
                    galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent, "Open Gallery"), REQUEST_GALLERY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
//                System.out.println("REQUEST IMAGE CAPTURE WORKS");
//                Bundle extras = data.getExtras();
//                Bitmap imageBitmap = (Bitmap) extras.get("data");
//                ivThumbnails.setImageBitmap(imageBitmap);
            }
            if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
                System.out.println("REQUEST GALLERY WORKS");
                Uri dataImageUri = data.getData();
                String[] imageProjection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(dataImageUri, imageProjection, null,null,null);
                if (cursor != null){
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageProjection[0]);
                    part_image = cursor.getString(indexImage);
                    System.out.println("cursor terjangkau");
                    if(part_image != null)
                    {
                        System.out.println("partImage terjangkau");
                        File image = new File(part_image);
                        ivThumbnails.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
                    }
                }
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
