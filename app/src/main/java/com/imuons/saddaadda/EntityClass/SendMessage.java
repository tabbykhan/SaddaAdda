package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class SendMessage implements Serializable {
    String message, to_user, type;

    public SendMessage(String message, String to_user, String type) {
        this.message = message;
        this.to_user = to_user;
        this.type = type;

    }
}