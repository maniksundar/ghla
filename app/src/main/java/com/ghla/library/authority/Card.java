package com.ghla.library.authority;
import android.content.Context;

public class Card {
    String m_title;
    int m_image;
    int m_color;

    Card(String title, int image, int color) {
        this.m_title = title;
        this.m_image = image;
        this.m_color = color;
    }

    public String getTitle() {
        return m_title;
    }
    public int getImage() {
        return m_image;
    }
    public int getColor(){
        return m_color;
    }
}
