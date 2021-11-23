package com.example.myword;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Holder {
    public ImageView sound;
    public ImageView star;
    public ImageView change;
    public TextView english;
    public TextView meaning;
    public TextView pronounce;

    public Holder(View root) {
        sound = root.findViewById(R.id.sound_word1);
        star = root.findViewById(R.id.star_word);
        change = root.findViewById(R.id.change_word);
        english = root.findViewById(R.id.word_English);
        pronounce = root.findViewById(R.id.pronounce);
        meaning = root.findViewById(R.id.word_Meaning);
    }
}
