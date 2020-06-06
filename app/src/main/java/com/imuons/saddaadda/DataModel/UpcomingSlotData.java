package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpcomingSlotData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("slot_no")
    @Expose
    private Integer slotNo;
    @SerializedName("from_time")
    @Expose
    private String fromTime;
    @SerializedName("to_time")
    @Expose
    private String toTime;
    @SerializedName("product_id")
    @Expose
    private Object productId;
    @SerializedName("product_name")
    @Expose
    private Object productName;
    @SerializedName("winner_count")
    @Expose
    private Integer winnerCount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("from_date")
    @Expose
    private String fromDate;
    @SerializedName("from_daytime")
    @Expose
    private String fromDaytime;
    @SerializedName("to_date")
    @Expose
    private String toDate;
    @SerializedName("to_daytime")
    @Expose
    private String toDaytime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(Integer slotNo) {
        this.slotNo = slotNo;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public Object getProductId() {
        return productId;
    }

    public void setProductId(Object productId) {
        this.productId = productId;
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

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getFromDaytime() {
        return fromDaytime;
    }

    public void setFromDaytime(String fromDaytime) {
        this.fromDaytime = fromDaytime;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getToDaytime() {
        return toDaytime;
    }

    public void setToDaytime(String toDaytime) {
        this.toDaytime = toDaytime;
    }
}
