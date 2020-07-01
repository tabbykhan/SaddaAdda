package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class SaddaXTopUp implements Serializable {
    String slot_no , mode;

    public SaddaXTopUp(String slot_no, String mode) {
        this.slot_no = slot_no;
        this.mode = mode;
    }
}
