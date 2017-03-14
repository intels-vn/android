package com.its.its.model.entity;

import java.io.Serializable;

/**
 * Created by BiLac on 3/10/2017.
 */

public class DataReturn {
    private Object data;
    private String message;
    private String status;

    public DataReturn(Object data, String message, String status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public DataReturn(Object data) {
        this.data = data;
    }

    public void setData(Object data) {
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
