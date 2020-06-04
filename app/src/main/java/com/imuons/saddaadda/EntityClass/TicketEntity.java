package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class TicketEntity implements Serializable {

    String lenght,open,start;

    public TicketEntity(String lenght, String open, String start) {
        this.lenght = lenght;
        this.open = open;
        this.start = start;
    }
}
