package com.example.freestyly.myintentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tv = (TextView)findViewById(R.id.textView);
        Intent intent = getIntent();
        if(intent.hasExtra("WHICH_BUTTON")) {
            String which_button =intent.getStringExtra("WHICH_BUTTON");
            tv.append("  & Toller Button" + which_button);
        }
    }
}
