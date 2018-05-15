package com.ghla.library.authority;

public class User {
    private String name;
    private String email;
    private String phone;

    User(String name) {
        this.name = name;
    }

    public User getCurrentUser() {
        return new User(" Samuel Dogbaste");
    }
}
