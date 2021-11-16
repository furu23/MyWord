package com.example.myword;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.Locale;

public class word extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        final int extra = getIntent().getIntExtra("DAY", -1);
        if(extra == -1) {
            Toast.makeText(word.this, "ERROR", Toast.LENGTH_LONG).show();
            finish();
        }

        menu.DBHelper helper = new menu.DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String where = String.format(Locale.KOREA, "where day == '%s' ", "Day" + extra);
        Cursor cursor = db.rawQuery("select _id, english, sound, meaning " +
                "from tb_word " +
                where +
                "order by _id asc", null);

        CursorAdapter cursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.word_sub,
                cursor,
                new String[]{"english", "meaning"},
                new int[]{R.id.word_English, R.id.word_Meaning},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );

        ListView list = findViewById(R.id.word_list);
        list.setOnItemClickListener(this);
        list.setAdapter(cursorAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}