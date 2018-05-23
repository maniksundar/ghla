package com.ghla.library.authority;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

interface TopMost {
    public Fragment getTopMostFragment();
    void clearAll();
}

class FragmentStack {
    // TODO:// Decide if the following code is needed or not.
//
//    // 0 - Home, 1 - Report, 2 - Profile
//    private static final FragmentStack ourInstance = new FragmentStack();
//    static FragmentStack getInstance() {
//        return ourInstance;
//    }
//
//    private List<Stack> stacks;
//    private Map<String, Stack> stackMap;
//
//    private FragmentStack() {
//        this.stacks = new ArrayList<>();
//        this.stackMap = new HashMap<>();
//    }
//
//    public void newStack(Fragment fragment) {
//        Stack stack = new Stack();
//        this.stackMap.put(fragment.toString(), stack);
//    }
//
//    public Stack getFragment(int number){
//        return this.stacks.get(number);
//    }
//
//    public void addFragmentToStack(@NonNull Fragment fragment,@NonNull Stack stack){
//        stack.add(fragment);
//    }
}
