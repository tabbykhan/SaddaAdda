package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class LoginEntity implements Serializable {
    String user_id , password , device_type , device_token ;


    public LoginEntity(String user_id, String password,  String device_token) {
        this.user_id = user_id;
        this.password = password;
        this.device_type = "A";
        this.device_token = device_token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
