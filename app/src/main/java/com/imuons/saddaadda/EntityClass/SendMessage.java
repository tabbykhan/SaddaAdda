package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class SendMessage implements Serializable {
    String message, to_user, type;

    public SendMessage(String message) {
        this.message = message;
        this.to_user = "1";
        this.type = "text";

    }
}
