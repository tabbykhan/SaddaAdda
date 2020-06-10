package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WinningNumberData {
    @SerializedName("slot_no")
    @Expose
    private Integer slotNo;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("winner_count")
    @Expose
    private Integer winnerCount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("winner_datetime")
    @Expose
    private String winnerDatetime;
    @SerializedName("winner_date")
    @Expose
    private String winnerDate;
    @SerializedName("winner_time")
    @Expose
    private String winnerTime;
    @SerializedName("winning_status")
    @Expose
    private String winningStatus;
    @SerializedName("winning_amount")
    @Expose
    private Integer winningAmount;
    @SerializedName("topup_data")
    @Expose
    private ArrayList<TopupDatum> topupData = null;

    public Integer getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(Integer slotNo) {
        this.slotNo = slotNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
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

    public String getWinnerDatetime() {
        return winnerDatetime;
    }

    public void setWinnerDatetime(String winnerDatetime) {
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

    public String getWinningStatus() {
        return winningStatus;
    }

    public void setWinningStatus(String winningStatus) {
        this.winningStatus = winningStatus;
    }

    public Integer getWinningAmount() {
        return winningAmount;
    }

    public void setWinningAmount(Integer winningAmount) {
        this.winningAmount = winningAmount;
    }

    public ArrayList<TopupDatum> getTopupData() {
        return topupData;
    }

    public void setTopupData(ArrayList<TopupDatum> topupData) {
        this.topupData = topupData;
    }

}
