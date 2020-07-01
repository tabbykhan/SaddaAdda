package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class SaddaXEntity implements Serializable {

    String product_id, amount, user_id;
    String slot_no , mode;

    public SaddaXEntity(String product_id, String amount, String user_id, String slot_no, String mode) {
        this.product_id = product_id;
        this.amount = amount;
        this.user_id = user_id;
        this.slot_no = slot_no;
        this.mode = mode;
    }
}
