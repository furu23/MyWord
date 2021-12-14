package com.example.myword;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class showQuizFragment extends Fragment implements View.OnClickListener {

    int answer, result = 0;

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

        int[] rand = new int[5];
        for(int i = 0; i < 5; i++) {
            rand[i] = (int) (Math.random() * menu.WORD);
        }

        Button btn1 = getView().findViewById(R.id.btn1);
        Button btn2 = getView().findViewById(R.id.btn2);
        Button btn3 = getView().findViewById(R.id.btn3);
        Button btn4 = getView().findViewById(R.id.btn4);

        Cursor cursorMean = db.rawQuery("select meaning from tb_word", null);
        Cursor cursorEng = db.rawQuery("select english from tb_word", null);

        answer = (int) (Math.random() * 10 % 4);
        int random;

        if (MOD == 1) {
            Log.i("QUIZ", "MOD_ENGLISH");

            TextView problem = getView().findViewById(R.id.problem);
            cursorMean.moveToPosition(answer);
            problem.setText(cursorMean.getString(0));

            while ((random = (int) (Math.random() * menu.WORD)) == answer);
            cursorEng.moveToPosition(random);
            btn1.setText(cursorEng.getString(0));

            while ((random = (int) (Math.random() * menu.WORD)) == answer);
            cursorEng.moveToPosition(random);
            btn2.setText(cursorEng.getString(0));

            while ((random = (int) (Math.random() * menu.WORD)) == answer);
            cursorEng.moveToPosition(random);
            btn3.setText(cursorEng.getString(0));

            while ((random = (int) (Math.random() * menu.WORD)) == answer);
            cursorEng.moveToPosition(random);
            btn4.setText(cursorEng.getString(0));

            if (answer == 0) {
                cursorEng.moveToPosition(answer);
                btn1.setText(cursorEng.getString(0));
            }
            else if (answer == 1) {
                cursorEng.moveToPosition(answer);
                btn2.setText(cursorEng.getString(0));
            }
            else if (answer == 2) {
                cursorEng.moveToPosition(answer);
                btn3.setText(cursorEng.getString(0));
            }
            else if (answer == 3) {
                cursorEng.moveToPosition(answer);
                btn4.setText(cursorEng.getString(0));
            }

        }
        else if (MOD == 2) {
            Log.i("QUIZ", "MOD_MEANING");
            TextView problem = getView().findViewById(R.id.problem);
            cursorEng.moveToPosition(answer);
            problem.setText(cursorEng.getString(0));

            while ((random = (int) (Math.random() * menu.WORD)) == answer);
            cursorMean.moveToPosition(random);
            btn1.setText(cursorMean.getString(0));

            while ((random = (int) (Math.random() * menu.WORD)) == answer);
            cursorMean.moveToPosition(random);
            btn2.setText(cursorMean.getString(0));

            while ((random = (int) (Math.random() * menu.WORD)) == answer);
            cursorMean.moveToPosition(random);
            btn3.setText(cursorMean.getString(0));

            while ((random = (int) (Math.random() * menu.WORD)) == answer);
            cursorMean.moveToPosition(random);
            btn4.setText(cursorMean.getString(0));

            if (answer == 0) {
                cursorMean.moveToPosition(answer);
                btn1.setText(cursorMean.getString(0));
            }
            else if (answer == 1) {
                cursorMean.moveToPosition(answer);
                btn2.setText(cursorMean.getString(0));
            }
            else if (answer == 2) {
                cursorMean.moveToPosition(answer);
                btn3.setText(cursorMean.getString(0));
            }
            else if (answer == 3) {
                cursorMean.moveToPosition(answer);
                btn4.setText(cursorMean.getString(0));
            }
        }

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        cursorEng.close();
        cursorMean.close();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn1) {
            if(answer == 0) {
                Toast.makeText(getContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                result++;
            }
        }
        if(view.getId() == R.id.btn2) {
            if (answer == 1) {
                Toast.makeText(getContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                result++;
            }
        }
        if(view.getId() == R.id.btn3) {
            if (answer == 2) {
                Toast.makeText(getContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                result++;
            }
        }
        if(view.getId() == R.id.btn4) {
            if (answer == 3) {
                Toast.makeText(getContext(), "정답입니다!", Toast.LENGTH_SHORT).show();
                result++;
            }
        }
        getActivity().finish();
    }
}