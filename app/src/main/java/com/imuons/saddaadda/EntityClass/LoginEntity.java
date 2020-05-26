package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class LoginEntity implements Serializable {
    String user_id , password;

    public LoginEntity(String user_id, String password) {
        this.user_id = user_id;
        this.password = password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
