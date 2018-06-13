package com.ghla.library.authority;

import android.content.SharedPreferences;

public class Disk {
    
    private static Disk disk = null;

    private Disk() { }

    // static method to create instance of Singleton class
    public static Disk getInstance() {
        if (disk == null)
            disk = new Disk();

        return disk;
    }

    void saveToDisk(Report report){
        // Can be used as a wrapper to save in Shared Preferences.
        System.out.println(report);

    }
}
