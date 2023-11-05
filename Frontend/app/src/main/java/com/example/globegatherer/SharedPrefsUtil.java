package com.example.globegatherer;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsUtil {

    private static final String SHARED_PREFS_NAME = "MyPrefs";
    private static final String USERNAME_KEY = "USERNAME";

    public static void saveUsername(Context context, String username) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME_KEY, username);
        editor.apply();
    }

    public static String getUsername(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USERNAME_KEY, "");
    }
}
