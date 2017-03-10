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
    private String email;

    public User(String id, String useraccount, String phonenumber, String fullname, String password, String email) {
        this.id = id;
        this.useraccount = useraccount;
        this.phonenumber = phonenumber;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
