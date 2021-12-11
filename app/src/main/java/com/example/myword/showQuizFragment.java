package com.example.myword;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import java.util.Random;

public class showQuizFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("QUIZ", "F1 CreateView");
        return inflater.inflate(R.layout.quiz_fragment1, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i("QUIZ", "F1 ViewCreate");
        Bundle bundle = getArguments();
        int MOD = bundle.getInt("MOD", -1);

        menu.DBHelper dbHelper = new menu.DBHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Random random = new Random();

        if (MOD == 1) {
            int rd = random.nextInt(menu.WORD);
            Log.i("QUIZ", "MOD1");
        }
        else if (MOD == 2)
            ;

        Button btn1 = getView().findViewById(R.id.btn1);
        Button btn2 = getView().findViewById(R.id.btn2);
        Button btn3 = getView().findViewById(R.id.btn3);
        Button btn4 = getView().findViewById(R.id.btn4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn1) {
            ((Quiz) getActivity()).toFragTwo();
        }
    }
}