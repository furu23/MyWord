package com.example.myword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Quiz extends AppCompatActivity implements View.OnClickListener {
    private showQuizFragment f1;

    private Button btn1;
    private Button btn2;

    public FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Log.i("QUIZ", "quiz init");

        btn1 = findViewById(R.id.quiz_eng);
        btn2 = findViewById(R.id.quiz_kor);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        manager = getSupportFragmentManager();

        f1 = new showQuizFragment();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.quiz_eng) {             //영어 맞추기 기능
            Log.i("QUIZ", "button1 Clicked");
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);

            Bundle bundle = new Bundle();
            bundle.putInt("MOD", 1);
            f1.setArguments(bundle);

            if(!f1.isVisible()) {
                Log.i("QUIZ", "f1 init");
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.Container, f1);
                ft.commit();
            }
        }
        else if (view.getId() == R.id.quiz_kor) {       //뜻 맞추기 기능
            Log.i("QUIZ", "button2 Clicked");
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);

            Bundle bundle = new Bundle();
            bundle.putInt("MOD", 2);
            f1.setArguments(bundle);

            if(!f1.isVisible()){
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.Container, f1);
                ft.commit();
            }
        }
    }
}
