package com.ghla.library.authority;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;

class JSONHelper {

    Object deserializeJSON(String filename, Class c){
        try {
            Object object = c.newInstance();
            String json = readJSONFromFile(filename);
            Gson gson = new Gson();
            return gson.fromJson(json,object.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    String readJSONFromFile (String filename){
        String json = null;
        try {
            InputStream is = App.getContext().getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
