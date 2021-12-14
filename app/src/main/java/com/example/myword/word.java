package com.example.myword;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class word extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        final int extra = getIntent().getIntExtra("DAY", -1);

        menu.DBHelper helper = new menu.DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor;

        if(extra == -1) {
            cursor = db.rawQuery("select _id, english, sound, meaning, star " +
                    "from tb_word " +
                    "where star == 1 " +
                    "order by _id asc", null);
        }
        else {
            String where = String.format(Locale.KOREA, "where day == '%s' ", "Day" + extra);    //db에서 단어내용 가져옴
            cursor = db.rawQuery("select _id, english, sound, meaning, star " +
                    "from tb_word " +
                    where +
                    "order by _id asc", null);
        }


        ArrayList<Drive> data = new ArrayList<>();
        while(cursor.moveToNext()) {
            Drive drive = new Drive();
            drive.index = cursor.getInt(0);
            drive.english = cursor.getString(1);
            drive.pronounce = cursor.getString(2);
            drive.meaning = cursor.getString(3);
            drive.star = (1 == cursor.getInt(4));
            data.add(drive);
        }
        cursor.close();


        CustomAdapter customAdapter= new CustomAdapter(this, R.layout.word_sub, data);
        ListView list = findViewById(R.id.word_list);
        list.setAdapter(customAdapter);
    }

    private class CustomAdapter extends ArrayAdapter<Drive> implements TextToSpeech.OnInitListener {
        Context context;
        int resId;
        ArrayList<Drive> data;
        TextToSpeech tts = new TextToSpeech(word.this, this, TextToSpeech.Engine.ACTION_GET_SAMPLE_TEXT);


        public CustomAdapter(Context context, int resId, ArrayList<Drive> data){
            super(context, resId);
            this.context = context;
            this.resId = resId;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


            if(convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(resId, null);
                Holder holder = new Holder(convertView);
                convertView.setTag(holder);
            }
            Holder holder = (Holder) convertView.getTag();

            ImageView sound = holder.sound;
            ImageView star = holder.star;
            ImageView change = holder.change;
            TextView pronounce = holder.pronounce;
            TextView english = holder.english;
            TextView meaning = holder.meaning;

            final Drive r = data.get(position);

            english.setText(r.english);
            pronounce.setText(r.pronounce);
            meaning.setText(r.meaning);

            if(r.star)
                star.setImageResource(R.drawable.ic_baseline_star_24);
            else
                star.setImageResource(R.drawable.star);


            //---------------------Listener----------------------

            
            sound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("WORD_INFO", "SOUND_CLICKED");        //tts 사용
                    tts.speak(r.english, TextToSpeech.QUEUE_FLUSH, null, null);
                }
            });

            star.setOnClickListener(new View.OnClickListener() {            //즐겨찾기 기능
                @Override
                public void onClick(View view) {
                    Log.d("WORD_INFO", "STAR_CLICKED");
                    menu.DBHelper helper = new menu.DBHelper(word.this);
                    SQLiteDatabase db = helper.getWritableDatabase();

                    if(r.star) {
                        db.execSQL("UPDATE tb_word " +
                                "SET star = false " +
                                "WHERE _id = " + r.index);

                        r.star = false;
                        star.setImageResource(R.drawable.star);
                    }
                    else {
                        db.execSQL("UPDATE tb_word " +
                                "SET star = true " +
                                "WHERE _id = " + r.index);

                        r.star = true;
                        star.setImageResource(R.drawable.ic_baseline_star_24);
                    }
                }
            });

            change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("WORD_INFO", "CHANGE_CLICKED");
                    String tmp1 = english.getText().toString();
                    String tmp2 = meaning.getText().toString();
                    meaning.setText(tmp1);
                    english.setText(tmp2);
                }
            });


            return convertView;
        }

        @Override
        public void onInit(int i) {
            if(i == TextToSpeech.SUCCESS) {
                tts.setLanguage(Locale.ENGLISH);
                Log.i("TTS", "init succes");
            }
            else {
                Log.i("TTS", "init failed");
                Toast.makeText(getContext(), "NEED GOOGLE TTS SPEECH SERVICE", Toast.LENGTH_LONG).show();
            }
        }
    }
}