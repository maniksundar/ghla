package com.ghla.library.authority;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

class Membership {
    private ArrayList<Title> titles;
    private ArrayList<Subtitle> subtitles;
    private ArrayList<Question> questions;

    Membership() throws FileNotFoundException {
        String path = "membership.json";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        Gson gson = new Gson();
        gson.fromJson(bufferedReader,this.getClass());
    }
}
