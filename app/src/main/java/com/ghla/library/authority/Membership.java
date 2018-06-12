package com.ghla.library.authority;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class Membership extends Report{
    @SerializedName("titleList")
    protected List<Title> reportContent;
    Membership(){
        // Membership typically contains type1 titles.
    }
}