package com.martn.enjoytime.db;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Title: Juyixia
 * Description: ("系统设置数据")
 * Date 2015/7/31 11:49
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class OnePreference {

    public static final String PREFS_NAME = OnePreference.class.getSimpleName();
    public static final String PREF_VERSION_CODE = "pref_version_code";
    private static OnePreference preference;
    private SharedPreferences setting;

    public OnePreference(Context context) {
        setting = context.getSharedPreferences(PREFS_NAME, 0);
    }

    public static OnePreference getInstance(Context context) {
        if (preference == null)
            preference = new OnePreference(context);
        return preference;
    }

    public SharedPreferences.Editor getEditor() {
        return setting.edit();
    }



    public boolean getBoolean(String key) {
        return setting.getBoolean(key, false);
    }

    public float getFloat(String key) {
        return setting.getFloat(key, 0.0F);
    }

    public int getInt(String key) {
        return setting.getInt(key, 0);
    }

    public int getInt(String key, int def) {
        return setting.getInt(key, def);
    }

    public long getLong(String key) {
        return setting.getLong(key, 0L);
    }

    public String getString(String key) {
        return setting.getString(key, null);
    }

    public boolean getBoolean(String key1, boolean def) {
        return setting.getBoolean(key1, def);
    }

    public String getString(String key1, String def) {
        return setting.getString(key1, def);
    }


    public void putBoolean(String key, boolean def) {
        getEditor().putBoolean(key, def).commit();
    }


    public void putFloat(String key, float def) {
        getEditor().putFloat(key, def).commit();
    }

    public void putInt(String key, int def) {
        getEditor().putInt(key, def).commit();
    }

    public void putLong(String key, long def) {
        getEditor().putLong(key, def).commit();
    }

    public void putString(String key1, String def) {
        getEditor().putString(key1, def).commit();
    }

    public void remove(String key) {
        getEditor().remove(key).commit();
    }

    public void clearAll() {

    }



}

