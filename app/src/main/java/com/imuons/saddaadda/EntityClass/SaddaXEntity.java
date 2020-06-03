package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class SaddaXEntity implements Serializable {

    String product_id, amount, user_id;

    public SaddaXEntity(String product_id, String amount, String user_id) {
        this.product_id = product_id;
        this.amount = amount;
        this.user_id = user_id;
    }
}
