package com.example.myword;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.Locale;

public class menu extends AppCompatActivity{
    public static final int UNIT = 4;
    public static final int DAY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = getSharedPreferences("pref", MODE_PRIVATE);
        if (sp.getBoolean("INIT", true)) {
            Log.i("INFO_WORD", "First application start");
            DBHelper helper = new DBHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();

            Resources res = getResources();
            String[] input = res.getStringArray(R.array.start_data);

            for(int i = 0; i < input.length; i++) {
                db.execSQL("insert into tb_word (unit, day, english, sound, meaning) values (?, ?, ?, ?, ?)",
                        (String.format(Locale.KOREA, "Unit%d,Day%d," , i/UNIT + 1, i/ DAY + 1)
                                 + input[i]).split(","));
            }
            sp.edit().putBoolean("INIT", false).apply();
        }

        setContentView(R.layout.menuxml);
    }

    public void menuClickListener(View v)
    {
        if(v.getId() == R.id.menu1)
            startActivity(new Intent(getApplicationContext(), unit_word.class));
        else if (v.getId() == R.id.menu2)
            ;
        else if (v.getId() == R.id.menu3)
            startActivity(new Intent(getApplicationContext(), search.class));
    }

    public static class DBHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;

        public DBHelper(Context context) {
            super(context, "wordDB", null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String wordSQL = "create table tb_word " +
                    "(_id integer primary key autoincrement, " +
                    "unit, day, english, sound, meaning)";
            db.execSQL(wordSQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (newVersion == DATABASE_VERSION) {
                db.execSQL("drop table tb_word");
                onCreate(db);
            }
        }
    }
}