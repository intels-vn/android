package com.its.its.model.entity;

/**
 * Created by BiLac on 3/9/2017.
 */

public class User {
    private String id;
    private String useraccount;
    private String phonenumber;
    private String fullname;
    private String password;
    private Integer password_expired;
    private String email;
    private String is_active;

    public User(String id, String useraccount, String phonenumber, String fullname, String password, Integer password_expired, String email, String is_active) {
        this.id = id;
        this.useraccount = useraccount;
        this.phonenumber = phonenumber;
        this.fullname = fullname;
        this.password = password;
        this.password_expired = password_expired;
        this.email = email;
        this.is_active = is_active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount;
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

    public Integer getPassword_expired() {
        return password_expired;
    }

    public void setPassword_expired(Integer password_expired) {
        this.password_expired = password_expired;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
}
