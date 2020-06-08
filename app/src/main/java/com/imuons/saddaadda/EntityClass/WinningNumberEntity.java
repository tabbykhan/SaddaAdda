package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class WinningNumberEntity implements Serializable {
    String slot_no,start,length;

    public WinningNumberEntity(String slot_no, String start, String length) {
        this.slot_no = slot_no;
        this.start = start;
        this.length = length;
    }
}
