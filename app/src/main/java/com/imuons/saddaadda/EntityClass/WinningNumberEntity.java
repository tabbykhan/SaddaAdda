package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class WinningNumberEntity implements Serializable {
    String slot_no,start,length , mode;

    public WinningNumberEntity(String slot_no, String start, String length, String mode) {
        this.slot_no = slot_no;
        this.start = start;
        this.length = length;
        this.mode = mode;
    }
}
