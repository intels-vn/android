package com.its.its.model.entity;

/**
 * Created by Chinh Bui on 3/13/2017.
 */

public class RegisterAndLogin {
    private String id;
    private String token;

    public RegisterAndLogin() {
    }

    public RegisterAndLogin(String id, String token) {

        this.id = id;
        this.token = token;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
