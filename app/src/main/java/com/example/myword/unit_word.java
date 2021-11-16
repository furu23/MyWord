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
        Cursor cursor = db.rawQuery("select distinct unit from tb_word order by _id desc", null);

        int i = 1;
        while(cursor.moveToNext())
            linear.addView(makeUnit(i++));


        setContentView(scroll);
        cursor.close();
    }

    private View makeUnit(int i) {
        RelativeLayout layout = new RelativeLayout(this);
        layout.setLayoutParams(
                new RelativeLayout.LayoutParams (
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    getDP(100)
            )
        );
        layout.setOnClickListener(view -> {
            Intent intent = new Intent(unit_word.this, word_day.class);
            intent.putExtra("UNIT", i);
            startActivity(intent);
        });
        layout.addView(makeText(i));
        layout.addView(makeArrow());
        return layout;
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
        RelativeLayout.LayoutParams image_params = new RelativeLayout.LayoutParams (
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        image_params.
                addRule(RelativeLayout.CENTER_VERTICAL, 1);
        image_params.addRule(RelativeLayout.ALIGN_PARENT_END, 1);
        image.setLayoutParams(image_params);
        image.setImageResource(R.drawable.arrow);

        return image;
    }

    private TextView makeText(int i) {
        TextView text = new TextView(this);
        text.setText(String.format(Locale.KOREA, "Unit%d", i));
        text.setTextSize(24);

        RelativeLayout.LayoutParams text_params = new RelativeLayout.LayoutParams (
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        text_params.setMarginStart(getDP(15));
        text_params.addRule(RelativeLayout.CENTER_VERTICAL, 1);
        text.setLayoutParams(text_params);
        return text;
    }
}