package com.ghla.library.authority;

import java.util.List;

class Membership {
    private static final String MEMBERSHIP_JSON = "membership.json";
    private List<Title> titles;
    Membership(){
        // Membership typically contains type1 titles.
        Membership membership = (Membership) new JSONHelper().deserializeJSON(MEMBERSHIP_JSON,Membership.class);
    }
}