package com.example.myword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Locale;


public class word_day extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_day);

        Log.i("DAY", "DAY init");

        final int extra = getIntent().getIntExtra("UNIT", -1);
        if(extra == -1) {
            Toast.makeText(word_day.this, "ERROR", Toast.LENGTH_LONG).show();
            finish();
        }

        menu.DBHelper helper = new menu.DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String where = String.format(Locale.KOREA, "where unit == '%s' ", "Unit" + extra);
        Cursor cursor = db.rawQuery("select distinct day " +        //Column day 만 중복없이 가져옴
                "from tb_word " +
                where +
                "order by _id asc",
                null);
        cursor.moveToFirst();

        StringBuilder str = new StringBuilder(cursor.getString(0));
        while(cursor.moveToNext())
            str.append(" ").append(cursor.getString(0));

        GridView gridView = (GridView) findViewById(R.id.day);
        gridView.setOnItemClickListener(this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(         //어댑터 생성
                this,
                R.layout.day_sub,
                R.id.day_word,
                str.toString().split(" ")
        );
        gridView.setAdapter(arrayAdapter);

        cursor.close();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {     //클릭 시 day 정보 넘기면서 다음 액티비티 실행
        Intent intent = new Intent(this, word.class);
        intent.putExtra("DAY", menu.DAY * (getIntent().getIntExtra("UNIT", -1) - 1) + i + 1);
        startActivity(intent);
    }
}
