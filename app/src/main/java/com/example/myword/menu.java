package com.example.myword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class menu extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuxml);

        RelativeLayout menu1 = findViewById(R.id.menu1);
        RelativeLayout menu2 = findViewById(R.id.menu2);
        RelativeLayout menu3 = findViewById(R.id.menu3);

        menu1.setOnTouchListener(new menuTouchListner());
        menu2.setOnTouchListener(new menuTouchListner());
        menu3.setOnTouchListener(new menuTouchListner());

    }

    private class menuTouchListner implements View.OnTouchListener
    {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            int id;
            Log.d("Click", "menu");
            if((id = view.getId()) == R.id.menu1) {
                Log.d("Click", "menu1");
                Intent intent = new Intent(menu.this, word_day.class);
                startActivity(intent);
            }
            else if(id == R.id.menu2)
                Log.d("Click", "menu2");    //단어 퀴즈 액티비티에 연결
            else if(id == R.id.menu3) {
                Log.d("Click", "menu3");
                Intent intent = new Intent(menu.this, search.class);
                startActivity(intent);
            }
            return true;
        }
    }

}