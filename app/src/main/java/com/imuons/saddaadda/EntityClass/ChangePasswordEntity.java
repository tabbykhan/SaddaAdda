package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class ChangePasswordEntity implements Serializable {

        String old_password, new_password,confirm_password,otp;

    public ChangePasswordEntity(String old_password, String new_password, String confirm_password, String otp) {
        this.old_password = old_password;
        this.new_password = new_password;
        this.confirm_password = confirm_password;
        this.otp = otp;
    }
}
