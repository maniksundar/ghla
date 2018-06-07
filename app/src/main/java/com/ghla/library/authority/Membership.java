package com.ghla.library.authority;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

class Membership {
    private static final String MEMBERSHIP_JSON = "membership.json";
    private List<Title> titles;
    Membership(){
        // Membership typically contains type1 titles.
        loadMembershipJSON();
    }

    void loadMembershipJSON(){
        try {
            String json = readJSONFromFile(MEMBERSHIP_JSON);
            Gson gson = new Gson();
            Membership membership = gson.fromJson(json,Membership.class);
            System.out.print(membership);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

class DataModelType1{
    private List<Title> titles;
}
