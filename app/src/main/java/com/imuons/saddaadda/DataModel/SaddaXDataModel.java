package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaddaXDataModel {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("top_up_wallet_balance")
    @Expose
    private String topUpWalletBalance;
    @SerializedName("slot_no")
    @Expose
    private String slotNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getTopUpWalletBalance() {
        return topUpWalletBalance;
    }

    public void setTopUpWalletBalance(String topUpWalletBalance) {
        this.topUpWalletBalance = topUpWalletBalance;
    }

    public String getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(String slotNo) {
        this.slotNo = slotNo;
    }
}
