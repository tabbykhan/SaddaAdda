package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class UpcomingEntity implements Serializable {
    Integer start;
    String length;

    public UpcomingEntity(Integer start, String length) {
        this.start = start;
        this.length = length;
    }
}
