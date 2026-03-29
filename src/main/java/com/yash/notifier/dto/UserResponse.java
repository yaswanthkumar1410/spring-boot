package com.yash.notifier.dto;

public class UserResponse {
    private long id;
    private String name;
    private String email;

    public UserResponse(long id, String name, String email) {
        this.email = email;
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
