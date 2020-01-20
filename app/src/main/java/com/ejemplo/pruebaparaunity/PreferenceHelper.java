package com.ejemplo.pruebaparaunity;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private final String TIME = "TIME";
    private final String INTRO = "intro";
    private final String USERNAME = "USERNAME";
    private final String PASS = "PASS";
    private final String LEVEL = "LEVEL";
    private final String GIFTS = "GIFTS";
    private final String REIND = "REIND";
    private SharedPreferences app_prefs;
    private Context context;


    public PreferenceHelper(Context context) {
        app_prefs = context.getSharedPreferences("shared",
                Context.MODE_PRIVATE);
        this.context = context;
    }
    public void putIsLogin(boolean loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(INTRO, loginorout);
        edit.commit();
    }
    public boolean getIsLogin() {
        return app_prefs.getBoolean(INTRO, false);
    }
    public void putUsername(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(USERNAME, loginorout);
        edit.commit();
    }
    public String getUsername() {
        return app_prefs.getString(USERNAME, "");
    }

    public void putPass(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(PASS, loginorout);
        edit.commit();
    }
    public String getPass() {
        return app_prefs.getString(PASS, "");
    }

    public void putTime(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(TIME, loginorout);
        edit.commit();
    }

    public void putLevel(String level) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(LEVEL, level);
        edit.commit();
    }

    public void putGifts(String gifts) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(GIFTS, gifts);
        edit.commit();
    }

    public void putReindeers(String reindeers) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(REIND, reindeers);
        edit.commit();
    }
}
