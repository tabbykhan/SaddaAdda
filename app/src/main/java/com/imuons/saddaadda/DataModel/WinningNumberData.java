package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WinningNumberData {
    @SerializedName("slot_no")
    @Expose
    private Integer slotNo;
    @SerializedName("product_name")
    @Expose
    private Object productName;
    @SerializedName("winner_count")
    @Expose
    private Integer winnerCount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("winner_datetime")
    @Expose
    private Object winnerDatetime;
    @SerializedName("winner_date")
    @Expose
    private String winnerDate;
    @SerializedName("winner_time")
    @Expose
    private String winnerTime;

    public Integer getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(Integer slotNo) {
        this.slotNo = slotNo;
    }

    public Object getProductName() {
        return productName;
    }

    public void setProductName(Object productName) {
        this.productName = productName;
    }

    public Integer getWinnerCount() {
        return winnerCount;
    }

    public void setWinnerCount(Integer winnerCount) {
        this.winnerCount = winnerCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getWinnerDatetime() {
        return winnerDatetime;
    }

    public void setWinnerDatetime(Object winnerDatetime) {
        this.winnerDatetime = winnerDatetime;
    }

    public String getWinnerDate() {
        return winnerDate;
    }

    public void setWinnerDate(String winnerDate) {
        this.winnerDate = winnerDate;
    }

    public String getWinnerTime() {
        return winnerTime;
    }

    public void setWinnerTime(String winnerTime) {
        this.winnerTime = winnerTime;
    }
}
