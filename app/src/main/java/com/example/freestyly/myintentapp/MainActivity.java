package com.example.freestyly.myintentapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public Button btnOpenWebURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.btnStartActivity)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start1();
            }
        });

        ((Button) findViewById(R.id.btnStartActivityWithExtra)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start2();
            }
        });

        ((Button) findViewById(R.id.btnDialAction)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start3();
            }
        });

        ((Button) findViewById(R.id.btnOpenWebURL)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start4();
            }
        });

        ((Button) findViewById(R.id.btnSendEMail)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start5();
            }
        });

        ((Button) findViewById(R.id.btnPicImage)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start6();
            }
        });

    }

    public void start1() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    public void start2() {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("WHICH_BUTTON", " JAAAAA");
        startActivity(intent);
    }

    public void start3() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01761234567"));
        startActivity(intent);
    }

    public void start4() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.hs-niederrhein.de/"));
        startActivity(intent);
    }

    public void start5() {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"alda@trashmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "sub");
        email.putExtra(Intent.EXTRA_TEXT, "text");
        email.setType("message/rfc822");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

    public static final int REQUEST_PICK_IMAGE = 1001;
    public void start6() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_PICK_IMAGE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri imageURI = intent.getData();
            String[] projection= {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(imageURI, projection, null,null,null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(projection[0]);
            String path = cursor.getString(columnIndex);
            cursor.close();

            Bitmap image = BitmapFactory.decodeFile(path);
            Log.d("demo", "Image received, width=" + image.getWidth());
            ImageView imageView = (ImageView)findViewById(R.id.imageView);
            imageView.setImageBitmap(image);
        }
    }
}

