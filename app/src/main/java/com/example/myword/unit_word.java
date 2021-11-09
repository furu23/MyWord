package com.example.myword;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class unit_word extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_word);
        LinearLayout a = findViewById(R.id.wrong1);

    }
    
    private class MyTouchListner implements View.OnTouchListener
    {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            Log.d("buttons", "onTouch: button");
            return true;
        }
    }

    
}