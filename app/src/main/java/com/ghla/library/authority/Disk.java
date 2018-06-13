package com.ghla.library.authority;

import android.content.Context;
import android.content.SharedPreferences;

public class Disk {

    private static final String DEFAULT ="default";
    private static Disk disk = null;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static Context mContext;

    private Disk(String file) {
        mContext = App.getContext();
        preferences = mContext.getSharedPreferences(file,Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    // static method to create instance of Singleton class
    public static Disk getInstance() {
        if (disk == null)
            disk = new Disk(DEFAULT);
        return disk;
    }

    public static boolean saveReportAs(String reportJSON, String file){
        editor.putString(file, reportJSON);
        return editor.commit();
    }

    public static String getReport(String file){
        return preferences.getString(file, null);
    }
}
