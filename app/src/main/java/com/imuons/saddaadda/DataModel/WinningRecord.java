package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WinningRecord {
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("slot_no")
    @Expose
    private Integer slotNo;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("winning_amount")
    @Expose
    private Integer winningAmount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getWinningAmount() {
        return winningAmount;
    }

    public void setWinningAmount(Integer winningAmount) {
        this.winningAmount = winningAmount;
    }

}
