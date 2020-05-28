package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class ChangePinEntity implements Serializable {
    String old_pin, new_pin ,  confirm_pin , otp;

    public ChangePinEntity(String old_pin, String new_pin, String confirm_pin, String otp) {
        this.old_pin = old_pin;
        this.new_pin = new_pin;
        this.confirm_pin = confirm_pin;
        this.otp = otp;
    }
}
