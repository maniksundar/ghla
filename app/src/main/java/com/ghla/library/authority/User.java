package com.ghla.library.authority;

public class User extends Person{
    private String name;
    private String email;
    private String phone;

    // Public methods
    User(String name) {
        this.name = name;
    }

    static public User getCurrentUser() {
        return new User(" Samuel Dogbaste");
    }

    String getName(){
        return this.name;
    }

    // Private methods

}
