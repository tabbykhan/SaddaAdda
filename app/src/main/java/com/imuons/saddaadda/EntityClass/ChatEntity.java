package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class ChatEntity implements Serializable {
    String to_user;

    public ChatEntity(String to_user) {
        this.to_user = to_user;
    }
}
