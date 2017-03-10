package com.its.its.model.entity;


import java.io.Serializable;

/**
 * Created by Chinh Bui on 3/10/2017.
 */

public class TestDuLieu implements Serializable {
    private String id;
    private Token token;

    public TestDuLieu() {
    }

    public TestDuLieu(String id, Token token) {

        this.id = id;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
