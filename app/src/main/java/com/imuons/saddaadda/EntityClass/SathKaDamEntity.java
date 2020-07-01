package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class SathKaDamEntity implements Serializable {
    String product_id , user_id , amount , mode;

    public SathKaDamEntity(String product_id, String user_id, String amount, String mode) {
        this.product_id = product_id;
        this.user_id = user_id;
        this.amount = amount;
        this.mode = mode;
    }
}
