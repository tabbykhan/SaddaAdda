package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class ResetPinEntity implements Serializable {
    String user_id,otp,new_pin,confirm_pin;

    public ResetPinEntity(String user_id, String otp, String new_pin, String confirm_pin) {
        this.user_id = user_id;
        this.otp = otp;
        this.new_pin = new_pin;
        this.confirm_pin = confirm_pin;
    }
}
