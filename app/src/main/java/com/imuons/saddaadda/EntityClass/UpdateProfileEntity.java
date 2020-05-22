package com.imuons.saddaadda.EntityClass;

public class UpdateProfileEntity {

    String fullname, mobile, account_no, bank_name,branch_name,ifsc_code,tez_no,phonepe_no;

    public UpdateProfileEntity(String fullname, String mobile, String account_no, String bank_name, String branch_name, String ifsc_code, String tez_no, String phonepe_no) {
        this.fullname = fullname;
        this.mobile = mobile;
        this.account_no = account_no;

        this.bank_name = bank_name;
        this.branch_name = branch_name;
        this.ifsc_code = ifsc_code;
        this.tez_no = tez_no;
        this.phonepe_no = phonepe_no;
    }
}
