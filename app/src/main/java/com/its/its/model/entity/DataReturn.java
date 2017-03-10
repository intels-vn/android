package com.its.its.model.entity;

/**
 * Created by BiLac on 3/10/2017.
 */

public class DataReturn {
    private String data;
    private String message;
    private String status;

    public DataReturn(String data, String message, String status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
