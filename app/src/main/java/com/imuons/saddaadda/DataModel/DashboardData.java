package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashboardData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("invite_code")
    @Expose
    private String inviteCode;
    @SerializedName("ref_user_id")
    @Expose
    private String refUserId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("nominee_name")
    @Expose
    private String nomineeName;
    @SerializedName("relation")
    @Expose
    private String relation;
    @SerializedName("pin_number")
    @Expose
    private String pinNumber;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("account_no")
    @Expose
    private String accountNo;
    @SerializedName("holder_name")
    @Expose
    private String holderName;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("branch_name")
    @Expose
    private String branchName;
    @SerializedName("pan_no")
    @Expose
    private String panNo;
    @SerializedName("ifsc_code")
    @Expose
    private String ifscCode;
    @SerializedName("btc_address")
    @Expose
    private String btcAddress;
    @SerializedName("encryptpass")
    @Expose
    private String encryptpass;
    @SerializedName("tr_passwd")
    @Expose
    private String trPasswd;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("l_c_count")
    @Expose
    private Integer lCCount;
    @SerializedName("r_c_count")
    @Expose
    private Integer rCCount;
    @SerializedName("l_bv")
    @Expose
    private Integer lBv;
    @SerializedName("r_bv")
    @Expose
    private Integer rBv;
    @SerializedName("vl_bv")
    @Expose
    private String vlBv;
    @SerializedName("vr_bv")
    @Expose
    private String vrBv;
    @SerializedName("confirmed_users")
    @Expose
    private Integer confirmedUsers;
    @SerializedName("virtual_parent_id")
    @Expose
    private Integer virtualParentId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("roi_stop")
    @Expose
    private Integer roiStop;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("ethereum")
    @Expose
    private String ethereum;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("pincode1")
    @Expose
    private String pincode1;
    @SerializedName("state1")
    @Expose
    private String state1;
    @SerializedName("city1")
    @Expose
    private String city1;
    @SerializedName("delivery_status")
    @Expose
    private String deliveryStatus;
    @SerializedName("delivery_note")
    @Expose
    private String deliveryNote;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("old_status")
    @Expose
    private Integer oldStatus;
    @SerializedName("delivery_mode")
    @Expose
    private String deliveryMode;
    @SerializedName("pdc_id")
    @Expose
    private Integer pdcId;
    @SerializedName("verifyaccountstatus")
    @Expose
    private Integer verifyaccountstatus;
    @SerializedName("verifytoken")
    @Expose
    private String verifytoken;
    @SerializedName("mobileverify_status")
    @Expose
    private Integer mobileverifyStatus;
    @SerializedName("mobile_otp")
    @Expose
    private String mobileOtp;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("tempf")
    @Expose
    private String tempf;
    @SerializedName("topup_status")
    @Expose
    private String topupStatus;
    @SerializedName("notification")
    @Expose
    private Integer notification;
    @SerializedName("stop_roi_time")
    @Expose
    private String stopRoiTime;
    @SerializedName("pindate")
    @Expose
    private String pindate;
    @SerializedName("confirm_date")
    @Expose
    private String confirmDate;
    @SerializedName("google2fa_secret")
    @Expose
    private String google2faSecret;
    @SerializedName("google2fa_status")
    @Expose
    private String google2faStatus;
    @SerializedName("service_date3")
    @Expose
    private String serviceDate3;
    @SerializedName("dispatch_date")
    @Expose
    private String dispatchDate;
    @SerializedName("delivery_date")
    @Expose
    private String deliveryDate;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("eth_address")
    @Expose
    private String ethAddress;
    @SerializedName("ltc_address")
    @Expose
    private String ltcAddress;
    @SerializedName("factor_status")
    @Expose
    private String factorStatus;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("paytm_no")
    @Expose
    private String paytmNo;
    @SerializedName("tez_no")
    @Expose
    private String tezNo;
    @SerializedName("phonepe_no")
    @Expose
    private String phonepeNo;
    @SerializedName("mobikwik_no")
    @Expose
    private String mobikwikNo;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("adminreg")
    @Expose
    private Integer adminreg;
    @SerializedName("binaryapplied")
    @Expose
    private String binaryapplied;
    @SerializedName("ref_fullname")
    @Expose
    private String refFullname;
    @SerializedName("sponser_id")
    @Expose
    private String sponserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getRefUserId() {
        return refUserId;
    }

    public void setRefUserId(String refUserId) {
        this.refUserId = refUserId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBtcAddress() {
        return btcAddress;
    }

    public void setBtcAddress(String btcAddress) {
        this.btcAddress = btcAddress;
    }

    public String getEncryptpass() {
        return encryptpass;
    }

    public void setEncryptpass(String encryptpass) {
        this.encryptpass = encryptpass;
    }

    public String getTrPasswd() {
        return trPasswd;
    }

    public void setTrPasswd(String trPasswd) {
        this.trPasswd = trPasswd;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getLCCount() {
        return lCCount;
    }

    public void setLCCount(Integer lCCount) {
        this.lCCount = lCCount;
    }

    public Integer getRCCount() {
        return rCCount;
    }

    public void setRCCount(Integer rCCount) {
        this.rCCount = rCCount;
    }

    public Integer getLBv() {
        return lBv;
    }

    public void setLBv(Integer lBv) {
        this.lBv = lBv;
    }

    public Integer getRBv() {
        return rBv;
    }

    public void setRBv(Integer rBv) {
        this.rBv = rBv;
    }

    public String getVlBv() {
        return vlBv;
    }

    public void setVlBv(String vlBv) {
        this.vlBv = vlBv;
    }

    public String getVrBv() {
        return vrBv;
    }

    public void setVrBv(String vrBv) {
        this.vrBv = vrBv;
    }

    public Integer getConfirmedUsers() {
        return confirmedUsers;
    }

    public void setConfirmedUsers(Integer confirmedUsers) {
        this.confirmedUsers = confirmedUsers;
    }

    public Integer getVirtualParentId() {
        return virtualParentId;
    }

    public void setVirtualParentId(Integer virtualParentId) {
        this.virtualParentId = virtualParentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRoiStop() {
        return roiStop;
    }

    public void setRoiStop(Integer roiStop) {
        this.roiStop = roiStop;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEthereum() {
        return ethereum;
    }

    public void setEthereum(String ethereum) {
        this.ethereum = ethereum;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPincode1() {
        return pincode1;
    }

    public void setPincode1(String pincode1) {
        this.pincode1 = pincode1;
    }

    public String getState1() {
        return state1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(Integer oldStatus) {
        this.oldStatus = oldStatus;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public Integer getPdcId() {
        return pdcId;
    }

    public void setPdcId(Integer pdcId) {
        this.pdcId = pdcId;
    }

    public Integer getVerifyaccountstatus() {
        return verifyaccountstatus;
    }

    public void setVerifyaccountstatus(Integer verifyaccountstatus) {
        this.verifyaccountstatus = verifyaccountstatus;
    }

    public String getVerifytoken() {
        return verifytoken;
    }

    public void setVerifytoken(String verifytoken) {
        this.verifytoken = verifytoken;
    }

    public Integer getMobileverifyStatus() {
        return mobileverifyStatus;
    }

    public void setMobileverifyStatus(Integer mobileverifyStatus) {
        this.mobileverifyStatus = mobileverifyStatus;
    }

    public String getMobileOtp() {
        return mobileOtp;
    }

    public void setMobileOtp(String mobileOtp) {
        this.mobileOtp = mobileOtp;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTempf() {
        return tempf;
    }

    public void setTempf(String tempf) {
        this.tempf = tempf;
    }

    public String getTopupStatus() {
        return topupStatus;
    }

    public void setTopupStatus(String topupStatus) {
        this.topupStatus = topupStatus;
    }

    public Integer getNotification() {
        return notification;
    }

    public void setNotification(Integer notification) {
        this.notification = notification;
    }

    public String getStopRoiTime() {
        return stopRoiTime;
    }

    public void setStopRoiTime(String stopRoiTime) {
        this.stopRoiTime = stopRoiTime;
    }

    public String getPindate() {
        return pindate;
    }

    public void setPindate(String pindate) {
        this.pindate = pindate;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getGoogle2faSecret() {
        return google2faSecret;
    }

    public void setGoogle2faSecret(String google2faSecret) {
        this.google2faSecret = google2faSecret;
    }

    public String getGoogle2faStatus() {
        return google2faStatus;
    }

    public void setGoogle2faStatus(String google2faStatus) {
        this.google2faStatus = google2faStatus;
    }

    public String getServiceDate3() {
        return serviceDate3;
    }

    public void setServiceDate3(String serviceDate3) {
        this.serviceDate3 = serviceDate3;
    }

    public String getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEthAddress() {
        return ethAddress;
    }

    public void setEthAddress(String ethAddress) {
        this.ethAddress = ethAddress;
    }

    public String getLtcAddress() {
        return ltcAddress;
    }

    public void setLtcAddress(String ltcAddress) {
        this.ltcAddress = ltcAddress;
    }

    public String getFactorStatus() {
        return factorStatus;
    }

    public void setFactorStatus(String factorStatus) {
        this.factorStatus = factorStatus;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaytmNo() {
        return paytmNo;
    }

    public void setPaytmNo(String paytmNo) {
        this.paytmNo = paytmNo;
    }

    public String getTezNo() {
        return tezNo;
    }

    public void setTezNo(String tezNo) {
        this.tezNo = tezNo;
    }

    public String getPhonepeNo() {
        return phonepeNo;
    }

    public void setPhonepeNo(String phonepeNo) {
        this.phonepeNo = phonepeNo;
    }

    public String getMobikwikNo() {
        return mobikwikNo;
    }

    public void setMobikwikNo(String mobikwikNo) {
        this.mobikwikNo = mobikwikNo;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getAdminreg() {
        return adminreg;
    }

    public void setAdminreg(Integer adminreg) {
        this.adminreg = adminreg;
    }

    public String getBinaryapplied() {
        return binaryapplied;
    }

    public void setBinaryapplied(String binaryapplied) {
        this.binaryapplied = binaryapplied;
    }

    public String getRefFullname() {
        return refFullname;
    }

    public void setRefFullname(String refFullname) {
        this.refFullname = refFullname;
    }

    public String getSponserId() {
        return sponserId;
    }

    public void setSponserId(String sponserId) {
        this.sponserId = sponserId;
    }

}
