package com.example.ATS.DTO;

import com.example.ATS.Enum.Roles;

public class RegisterUser {

    private String name;
    private String email;
    private String username;
    private String password;
    private Roles roles;


    public RegisterUser() {
    }

    public RegisterUser(String name, String email, String username, String password,  Roles roles) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
