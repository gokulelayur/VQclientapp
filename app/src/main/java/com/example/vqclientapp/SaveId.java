package com.example.vqclientapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveId {

    static final String ID = "ID";
    static final String depID="depID";


    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setId(Context ctx, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(ID, value);
        editor.commit();
    }

    public static String getId(Context ctx) {
        return getSharedPreferences(ctx).getString(ID, "defaut");
    }

    public static void setDepID(Context ctx,String value){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(depID,value);
        editor.commit();
    }
    public  static String getDepID(Context ctx){
        return getSharedPreferences(ctx).getString(depID,"defaut");
    }
}
