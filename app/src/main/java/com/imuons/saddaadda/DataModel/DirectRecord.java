package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DirectRecord {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("tax_amount")
    @Expose
    private String taxAmount;
    @SerializedName("amt_pin")
    @Expose
    private String amtPin;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("toUserId")
    @Expose
    private String toUserId;
    @SerializedName("fromUserId")
    @Expose
    private String fromUserId;
    @SerializedName("invoice_id")
    @Expose
    private String invoiceId;
    @SerializedName("notification")
    @Expose
    private Integer notification;
    @SerializedName("to_User")
    @Expose
    private String toUser;
    @SerializedName("from_fullname")
    @Expose
    private String fromFullname;
    @SerializedName("to_fullname")
    @Expose
    private String toFullname;
    @SerializedName("from_User")
    @Expose
    private String fromUser;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("laps_amount")
    @Expose
    private String lapsAmount;

    boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getAmtPin() {
        return amtPin;
    }

    public void setAmtPin(String amtPin) {
        this.amtPin = amtPin;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getNotification() {
        return notification;
    }

    public void setNotification(Integer notification) {
        this.notification = notification;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getFromFullname() {
        return fromFullname;
    }

    public void setFromFullname(String fromFullname) {
        this.fromFullname = fromFullname;
    }

    public String getToFullname() {
        return toFullname;
    }

    public void setToFullname(String toFullname) {
        this.toFullname = toFullname;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLapsAmount() {
        return lapsAmount;
    }

    public void setLapsAmount(String lapsAmount) {
        this.lapsAmount = lapsAmount;
    }
}
