package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TicketRecordModel {

    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("old_balance")
    @Expose
    private Integer oldBalance;
    @SerializedName("commit_id")
    @Expose
    private Integer commitId;
    @SerializedName("buy_amount")
    @Expose
    private String buyAmount;
    @SerializedName("link_time")
    @Expose
    private Integer linkTime;
    @SerializedName("tranid")
    @Expose
    private String tranid;
    @SerializedName("from_user")
    @Expose
    private String fromUser;
    @SerializedName("from_fullname")
    @Expose
    private String fromFullname;
    @SerializedName("from_mobile")
    @Expose
    private String fromMobile;
    @SerializedName("to_mobile")
    @Expose
    private String toMobile;
    @SerializedName("from_country")
    @Expose
    private String fromCountry;
    @SerializedName("to_user")
    @Expose
    private String toUser;
    @SerializedName("to_fullname")
    @Expose
    private String toFullname;
    @SerializedName("to_country")
    @Expose
    private String toCountry;
    @SerializedName("link_type")
    @Expose
    private String linkType;
    @SerializedName("current_date")
    @Expose
    private CurrentDateModel currentDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("assign_date")
    @Expose
    private String assignDate;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("ref_user_type")
    @Expose
    private String refUserType;
    @SerializedName("linktime_n_assigndate")
    @Expose
    private LinktimeNAssigndate linktimeNAssigndate;
    @SerializedName("confirm_date")
    @Expose
    private String confirmDate;
    @SerializedName("req_no")
    @Expose
    private Integer reqNo;
    @SerializedName("curr_timestamp")
    @Expose
    private String currTimestamp;
    @SerializedName("ass_timestamp")
    @Expose
    private Integer assTimestamp;
    @SerializedName("request_link_type")
    @Expose
    private Integer requestLinkType;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("progess_bar")
    @Expose
    private String progessBar;
    @SerializedName("toid")
    @Expose
    private Integer toid;
    @SerializedName("receipt_status")
    @Expose
    private Integer receiptStatus;
    @SerializedName("fromid")
    @Expose
    private Integer fromid;
    @SerializedName("assign_dateonly")
    @Expose
    private String assignDateonly;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(Integer oldBalance) {
        this.oldBalance = oldBalance;
    }

    public Integer getCommitId() {
        return commitId;
    }

    public void setCommitId(Integer commitId) {
        this.commitId = commitId;
    }

    public String getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(String buyAmount) {
        this.buyAmount = buyAmount;
    }

    public Integer getLinkTime() {
        return linkTime;
    }

    public void setLinkTime(Integer linkTime) {
        this.linkTime = linkTime;
    }

    public String getTranid() {
        return tranid;
    }

    public void setTranid(String tranid) {
        this.tranid = tranid;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getFromFullname() {
        return fromFullname;
    }

    public void setFromFullname(String fromFullname) {
        this.fromFullname = fromFullname;
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

    public String getFromCountry() {
        return fromCountry;
    }

    public void setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
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

    public String getToCountry() {
        return toCountry;
    }

    public void setToCountry(String toCountry) {
        this.toCountry = toCountry;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public CurrentDateModel getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(CurrentDateModel currentDate) {
        this.currentDate = currentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRefUserType() {
        return refUserType;
    }

    public void setRefUserType(String refUserType) {
        this.refUserType = refUserType;
    }

    public LinktimeNAssigndate getLinktimeNAssigndate() {
        return linktimeNAssigndate;
    }

    public void setLinktimeNAssigndate(LinktimeNAssigndate linktimeNAssigndate) {
        this.linktimeNAssigndate = linktimeNAssigndate;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Integer getReqNo() {
        return reqNo;
    }

    public void setReqNo(Integer reqNo) {
        this.reqNo = reqNo;
    }

    public String getCurrTimestamp() {
        return currTimestamp;
    }

    public void setCurrTimestamp(String currTimestamp) {
        this.currTimestamp = currTimestamp;
    }

    public Integer getAssTimestamp() {
        return assTimestamp;
    }

    public void setAssTimestamp(Integer assTimestamp) {
        this.assTimestamp = assTimestamp;
    }

    public Integer getRequestLinkType() {
        return requestLinkType;
    }

    public void setRequestLinkType(Integer requestLinkType) {
        this.requestLinkType = requestLinkType;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getProgessBar() {
        return progessBar;
    }

    public void setProgessBar(String progessBar) {
        this.progessBar = progessBar;
    }

    public Integer getToid() {
        return toid;
    }

    public void setToid(Integer toid) {
        this.toid = toid;
    }

    public Integer getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(Integer receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    public Integer getFromid() {
        return fromid;
    }

    public void setFromid(Integer fromid) {
        this.fromid = fromid;
    }

    public String getAssignDateonly() {
        return assignDateonly;
    }

    public void setAssignDateonly(String assignDateonly) {
        this.assignDateonly = assignDateonly;
    }
}
