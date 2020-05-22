package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileDataModel {

    @SerializedName("sponser")
    @Expose
    private String sponser;
    @SerializedName("sponser_fullname")
    @Expose
    private String sponserFullname;
    @SerializedName("sponser_country")
    @Expose
    private String sponserCountry;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("btc_address")
    @Expose
    private String btcAddress;
    @SerializedName("ethereum")
    @Expose
    private String ethereum;
    @SerializedName("eth_address")
    @Expose
    private String ethAddress;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("ref_user_id")
    @Expose
    private String refUserId;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("active_status")
    @Expose
    private String activeStatus;
    @SerializedName("google2fa_status")
    @Expose
    private String google2faStatus;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("holder_name")
    @Expose
    private String holderName;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("branch_name")
    @Expose
    private String branchName;
    @SerializedName("ifsc_code")
    @Expose
    private String ifscCode;
    @SerializedName("account_no")
    @Expose
    private String accountNo;
    @SerializedName("tez_no")
    @Expose
    private String tezNo;
    @SerializedName("phonepe_no")
    @Expose
    private String phonepeNo;

    public String getSponser() {
        return sponser;
    }

    public void setSponser(String sponser) {
        this.sponser = sponser;
    }

    public String getSponserFullname() {
        return sponserFullname;
    }

    public void setSponserFullname(String sponserFullname) {
        this.sponserFullname = sponserFullname;
    }

    public String getSponserCountry() {
        return sponserCountry;
    }

    public void setSponserCountry(String sponserCountry) {
        this.sponserCountry = sponserCountry;
    }

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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBtcAddress() {
        return btcAddress;
    }

    public void setBtcAddress(String btcAddress) {
        this.btcAddress = btcAddress;
    }

    public String getEthereum() {
        return ethereum;
    }

    public void setEthereum(String ethereum) {
        this.ethereum = ethereum;
    }

    public String getEthAddress() {
        return ethAddress;
    }

    public void setEthAddress(String ethAddress) {
        this.ethAddress = ethAddress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRefUserId() {
        return refUserId;
    }

    public void setRefUserId(String refUserId) {
        this.refUserId = refUserId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getGoogle2faStatus() {
        return google2faStatus;
    }

    public void setGoogle2faStatus(String google2faStatus) {
        this.google2faStatus = google2faStatus;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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
}
