package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransRecord {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("tr_type")
    @Expose
    private String trType;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("prev_balance")
    @Expose
    private Integer prevBalance;
    @SerializedName("balance")
    @Expose
    private Integer balance;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("wallet")
    @Expose
    private String wallet;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTrType() {
        return trType;
    }

    public void setTrType(String trType) {
        this.trType = trType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrevBalance() {
        return prevBalance;
    }

    public void setPrevBalance(Integer prevBalance) {
        this.prevBalance = prevBalance;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }
}
