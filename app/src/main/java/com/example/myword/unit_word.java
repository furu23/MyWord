package com.example.myword;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Locale;

public class unit_word extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scroll = new ScrollView(this);
        scroll.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        );

        LinearLayout linear = new LinearLayout(this);
        linear.setOrientation(LinearLayout.VERTICAL);
        linear.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        scroll.addView(linear);


        menu.DBHelper helper = new menu.DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select unit from tb_word order by _id desc", null);
        cursor.moveToFirst();


        int i = 1;
        String A = cursor.getString(0), B = null;
        while(cursor.moveToNext()) {
            if(!A.equals(B)) {
                linear.addView(makeUnit(i++));
                B = A;
            }
            A = cursor.getString(0);
        }

        cursor.close();
        helper.close();

        setContentView(scroll);
    }

    private View makeUnit(int i) {
        RelativeLayout rlayout = new RelativeLayout(this);
        rlayout.setLayoutParams(
                new RelativeLayout.LayoutParams (
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    getDP(100)
            )
        );
        rlayout.setOnClickListener(view -> {
            Intent intent = new Intent(unit_word.this, word_day.class);
            intent.putExtra("UNIT", i);
            startActivity(intent);
        });
        rlayout.addView(makeText(i));
        rlayout.addView(makeArrow());
        return rlayout;
    }

    private int getDP(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                (float) dp,
                getApplicationContext().getResources().getDisplayMetrics()
        );
    }

    private ImageView makeArrow() {
        ImageView image = new ImageView(this);
        RelativeLayout.LayoutParams imageparams = new RelativeLayout.LayoutParams (
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        imageparams.addRule(RelativeLayout.CENTER_VERTICAL, 1);
        imageparams.addRule(RelativeLayout.ALIGN_PARENT_END, 1);
        image.setLayoutParams(imageparams);
        image.setImageResource(R.drawable.arrow);

        return image;
    }

    private TextView makeText(int i) {
        TextView text = new TextView(this);
        text.setText(String.format(Locale.KOREA, "Unit%d", i));
        text.setTextSize(24);

        RelativeLayout.LayoutParams textparams = new RelativeLayout.LayoutParams (
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        textparams.setMarginStart(getDP(15));
        textparams.addRule(RelativeLayout.CENTER_VERTICAL, 1);
        text.setLayoutParams(textparams);
        return text;
    }
}