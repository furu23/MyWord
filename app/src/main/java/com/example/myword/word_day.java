package com.example.myword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.*;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;


public class word_day extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_day);

        int extra = getIntent().getIntExtra("UNIT", -1);
    }

    public void dayClickListner(View v) {
        Intent intent = new Intent(getApplicationContext(), word.class);
        if (v.getId() == R.id.day1) {
            startActivity(intent);
        } else if (v.getId() == R.id.day2) {
            startActivity(new Intent(getApplicationContext(), word.class));
        }
    }
}
