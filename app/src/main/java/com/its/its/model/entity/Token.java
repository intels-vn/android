package com.its.its.model.entity;

import java.io.Serializable;

/**
 * Created by Chinh Bui on 3/10/2017.
 */

public class Token implements Serializable {
    private String code;
    private long from;
    private long to;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public Token() {

    }

    public Token(String code, long from, long to) {

        this.code = code;
        this.from = from;
        this.to = to;
    }
}
