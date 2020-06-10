package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FundTransData {
    @SerializedName("top_up_wallet_balance")
    @Expose
    private Integer topUpWalletBalance;
    @SerializedName("winning_wallet_balance")
    @Expose
    private Integer winningWalletBalance;

    public Integer getTopUpWalletBalance() {
        return topUpWalletBalance;
    }

    public void setTopUpWalletBalance(Integer topUpWalletBalance) {
        this.topUpWalletBalance = topUpWalletBalance;
    }

    public Integer getWinningWalletBalance() {
        return winningWalletBalance;
    }

    public void setWinningWalletBalance(Integer winningWalletBalance) {
        this.winningWalletBalance = winningWalletBalance;
    }
}
