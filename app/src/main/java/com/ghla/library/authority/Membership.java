package com.ghla.library.authority;

import java.util.List;

class Membership extends Report{
    //Cannot use titles as it's being used by Report
    public List<Title> titleList;
    Membership(){
        // Membership typically contains type1 titles.
    }
}