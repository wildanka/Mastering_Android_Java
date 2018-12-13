package com.example.dan.kamerakowaijava;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.support.v4.content.FileProvider.getUriForFile;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final int CAMERA_PERMISSION_REQUEST = 1995;
    private Button btnCamera;
    private Button btnGallery;
    private Button btnUpload;
    private Button btnSaveCrop;
    private Button btnUploadBase64;
    private ImageView ivThumbnails;
    private CropImageView cropImageView;
    File storageDir;
    File outputPhotoFile; //the Image Files
    Uri photoURI;
    String mCurrentPhotoPath;
    String part_image;
    private static final int REQUEST_TAKE_PHOTO = 188;
    private static final int REQUEST_GALLERY = 189;


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
        btnSaveCrop= (Button) findViewById(R.id.btnSaveCrop);
        ivThumbnails = (ImageView) findViewById(R.id.iv_thumb);
        cropImageView = (CropImageView) findViewById(R.id.cropImageView);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //instant
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setCropShape(CropImageView.CropShape.OVAL)
                        .start(MainActivity.this);

            }
        });

        btnUploadBase64.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, FutureStudActivity.class);
                startActivity(in);
            }
        });

        btnSaveCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap cropped =  cropImageView.getCroppedImage(500, 500);
                if (cropped != null){
                    cropImageView.setImageBitmap(cropped);
                }
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
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                BitmapFactory.Options factoryOptions = new BitmapFactory.Options();
                Uri resultUri = result.getUri();
                Log.d(TAG,"RESULT URI : "+resultUri);
                Bitmap bitmap = BitmapFactory.decodeFile(resultUri.getPath(), factoryOptions);
                //if we want to compress the image, then we can use WEBP for the light image size
                //region compress image
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.WEBP,0, bos);
                //endregion
                ivThumbnails.setImageBitmap(bitmap);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Log.d(TAG,"Error : "+error);
            }
        }
    }

    //region take Picture

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
                photoURI = getUriForFile(this,
                        BuildConfig.APPLICATION_ID+".provider",
                        photoFile);
                System.out.println(photoURI);
                System.out.println("mCurrentPhotoPath : "+mCurrentPhotoPath);
                outputPhotoFile = new File(mCurrentPhotoPath);
                File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                File externalStorageDirectory = Environment.getExternalStorageDirectory();
                System.out.println("storageDir : "+storageDir.getAbsolutePath());
                System.out.println("externalStoragePublicDir : "+externalStoragePublicDirectory.getAbsolutePath());
                System.out.println("externalStorageDir : "+externalStorageDirectory.getAbsolutePath());

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "KameraKowaiJava" + timeStamp + "_";
        storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File buatan = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        System.out.println("STORAGEDIR : "+storageDir.getAbsolutePath());
        System.out.println("BUATAN : "+buatan.getAbsolutePath());

        File image = File.createTempFile(
                "KameraKowai",  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    //endregion take Picture
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
