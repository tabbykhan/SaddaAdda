package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoinsDataModel {
    @SerializedName("purchase_wallet")
    @Expose
    private Integer purchaseWallet;
    @SerializedName("winning_wallet")
    @Expose
    private Integer winningWallet;

    public Integer getPurchaseWallet() {
        return purchaseWallet;
    }

    public void setPurchaseWallet(Integer purchaseWallet) {
        this.purchaseWallet = purchaseWallet;
    }

    public Integer getWinningWallet() {
        return winningWallet;
    }

    public void setWinningWallet(Integer winningWallet) {
        this.winningWallet = winningWallet;
    }
}
