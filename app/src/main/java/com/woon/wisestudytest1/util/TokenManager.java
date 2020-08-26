package com.woon.wisestudytest1.util;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {

    private static final String PREFERENCES_NAME = "TOKEN_PREFERENCES";
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String DEFAULT_TOKEN = "";

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 토큰 저장하기
     * @param context
     * @param userToken
     */
    public static void setPreferences(Context context, String userToken){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(ACCESS_TOKEN, userToken);
        editor.apply();

    }

    /**
     * 토큰 불러오기
     * @param context
     * @param userToken
     * @return
     */
    public static String getPreferences(Context context, String userToken){
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(ACCESS_TOKEN, DEFAULT_TOKEN);
    }

    /**
     * 토큰 삭제하기
     * @param context
     */
    public static void remove(Context context) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(ACCESS_TOKEN);
        editor.apply();
    }

}
