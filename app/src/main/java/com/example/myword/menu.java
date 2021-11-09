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
            if(view.getId() == R.id.menu1) {        //단어장으로 연결
                Log.d("Menu", "Menu_word_day");
                Intent intent = new Intent(getApplicationContext(), word_day.class);
                startActivity(intent);
            }
            else if(view.getId() == R.id.menu2)
                Log.d("Menu", "Menu_quiz");    //단어 퀴즈 액티비티에 연결
            else if(view.getId() == R.id.menu3) {
                Log.d("Menu", "Menu_search");       //검색 액티비티로 연결
                Intent intent = new Intent(getApplicationContext(), search.class);
                startActivity(intent);

            }
            return false;
        }
    }

}