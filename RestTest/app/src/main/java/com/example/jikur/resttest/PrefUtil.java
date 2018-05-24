package com.example.jikur.resttest;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtil {


    public static final String PREF_FILE_NAME = "prefSWU";

    public static void setPref(Context context, String key, String value) {
        SharedPreferences pref =
                context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit(); //파일저장
    }

    public static String getPref(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return pref.getString(key, "");
    }


}
