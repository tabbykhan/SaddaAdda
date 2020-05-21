package com.imuons.saddaadda.EntityClass;

import java.io.Serializable;

public class RegitrationEntity implements Serializable {
    String user_id, fullname, password, mobile, ref_user_id;

    public RegitrationEntity(String user_id, String fullname, String password, String mobile, String ref_user_id) {
        this.user_id = user_id;
        this.fullname = fullname;
        this.password = password;
        this.mobile = mobile;
        this.ref_user_id = ref_user_id;
    }
}
