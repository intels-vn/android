package com.its.its.model.entity;

import java.io.Serializable;

/**
 * Created by BiLac on 3/9/2017.
 */

public class UserGroup implements Serializable {
    private String user_group_id;

    public String getUser_group_id() {
        return user_group_id;
    }

    public void setUser_group_id(String user_group_id) {
        this.user_group_id = user_group_id;
    }
}
