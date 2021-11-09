package com.example.myword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class word_day extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_day);

        RelativeLayout day1 = findViewById(R.id.day1);
        day1.setOnTouchListener(new dayTouchListner());

    }
}

class dayTouchListner implements View.OnTouchListener
{
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(view.getId() == R.id.day1) {
            Log.d("Menu", "Menu_word_day");

        }
        return false;
    }
}