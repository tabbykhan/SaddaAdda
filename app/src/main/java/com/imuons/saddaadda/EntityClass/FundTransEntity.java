package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class FundTransEntity implements Serializable {
    String amount;

    public FundTransEntity(String amount) {
        this.amount = amount;
    }
}
