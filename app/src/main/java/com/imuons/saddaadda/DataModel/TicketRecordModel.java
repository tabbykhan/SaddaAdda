package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TicketRecordModel {

    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("buy_amount")
    @Expose
    private String buyAmount;
    @SerializedName("to_user")
    @Expose
    private String toUser;
    @SerializedName("to_fullname")
    @Expose
    private String toFullname;
    @SerializedName("from_mobile")
    @Expose
    private String fromMobile;
    @SerializedName("to_mobile")
    @Expose
    private String toMobile;
    @SerializedName("assign_date")
    @Expose
    private String assignDate;
    @SerializedName("tranid")
    @Expose
    private String tranid;
    @SerializedName("from_fullname")
    @Expose
    private String fromFullname;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(String buyAmount) {
        this.buyAmount = buyAmount;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToFullname() {
        return toFullname;
    }

    public void setToFullname(String toFullname) {
        this.toFullname = toFullname;
    }

    public String getFromMobile() {
        return fromMobile;
    }

    public void setFromMobile(String fromMobile) {
        this.fromMobile = fromMobile;
    }

    public String getToMobile() {
        return toMobile;
    }

    public void setToMobile(String toMobile) {
        this.toMobile = toMobile;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public String getTranid() {
        return tranid;
    }

    public void setTranid(String tranid) {
        this.tranid = tranid;
    }

    public String getFromFullname() {
        return fromFullname;
    }

    public void setFromFullname(String fromFullname) {
        this.fromFullname = fromFullname;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    @SerializedName("from_user")
    @Expose
    private String fromUser;
}
