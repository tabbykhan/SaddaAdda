package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class SathKaDamEntity implements Serializable {
    String product_id , user_id , amount;

    public SathKaDamEntity(String product_id, String user_id, String amount) {
        this.product_id = product_id;
        this.user_id = user_id;
        this.amount = amount;
    }
}
