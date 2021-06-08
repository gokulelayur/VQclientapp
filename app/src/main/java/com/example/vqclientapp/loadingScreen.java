package com.example.vqclientapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public  class loadingScreen {
//    static Dialog dialog;

    static AlertDialog dialog;
    public static void startloading(Activity activity,String text){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.loading_screen,null);
        view.setClipToOutline(true);

        TextView loadingText;
        loadingText= view.findViewById(R.id.loadingText);
        loadingText.setText(text);

        builder.setView(view);
        builder.setCancelable(false);

        dialog=builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        dialog.setContentView(R.layout.loading_screen);
        dialog.show();
    }

    static void stoploading(){
        dialog.dismiss();
    }
}

