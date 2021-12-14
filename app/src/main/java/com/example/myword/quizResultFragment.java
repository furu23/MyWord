package com.example.myword;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class quizResultFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle("퀴즈 결과");
            builder.setMessage("TEST");
            builder.setPositiveButton("틀린 문제 보기", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ;
                }
            });
            builder.setNegativeButton("넘어가기", null);
            AlertDialog dialog = builder.create();
            return dialog;
    }
}