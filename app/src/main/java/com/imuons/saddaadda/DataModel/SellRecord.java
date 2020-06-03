package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellRecord {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("old_balance")
    @Expose
    private Integer oldBalance;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("withdraw_id")
    @Expose
    private Integer withdrawId;
    @SerializedName("sell_date")
    @Expose
    private String sellDate;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("complete_date")
    @Expose
    private Object completeDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("req_no")
    @Expose
    private Integer reqNo;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("withdraw_type")
    @Expose
    private String withdrawType;
    @SerializedName("current_date")
    @Expose
    private CurrentDate currentDate;
    @SerializedName("gh_count")
    @Expose
    private Integer ghCount;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(Integer oldBalance) {
        this.oldBalance = oldBalance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(Integer withdrawId) {
        this.withdrawId = withdrawId;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Object getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Object completeDate) {
        this.completeDate = completeDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getReqNo() {
        return reqNo;
    }

    public void setReqNo(Integer reqNo) {
        this.reqNo = reqNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(String withdrawType) {
        this.withdrawType = withdrawType;
    }

    public CurrentDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(CurrentDate currentDate) {
        this.currentDate = currentDate;
    }

    public Integer getGhCount() {
        return ghCount;
    }

    public void setGhCount(Integer ghCount) {
        this.ghCount = ghCount;
    }
}
