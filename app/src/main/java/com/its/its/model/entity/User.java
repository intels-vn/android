package com.its.its.model.entity;

import java.io.Serializable;

/**
 * Created by BiLac on 3/9/2017.
 */

public class User implements Serializable {
    private String id;
    private String username;
    private String phonenumber;
    private String phone_received_exchange;
    private String fullname;
    private String password;
    private String email;

    public User(String id, String username, String phonenumber, String phone_received_exchange, String fullname, String password, String email) {
        this.id = id;
        this.username = username;
        this.phonenumber = phonenumber;
        this.phone_received_exchange = phone_received_exchange;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
    }

    public User() {

    }

    public String getPhone_received_exchange() {

        return phone_received_exchange;
    }

    public void setPhone_received_exchange(String phone_received_exchange) {
        this.phone_received_exchange = phone_received_exchange;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
