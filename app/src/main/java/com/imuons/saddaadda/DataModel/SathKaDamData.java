package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SathKaDamData {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("pin")
    @Expose
    private Double pin;
    @SerializedName("dice1")
    @Expose
    private Double dice1;
    @SerializedName("dice2")
    @Expose
    private Double dice2;
    @SerializedName("dice_sum")
    @Expose
    private Double diceSum;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("winning_amount")
    @Expose
    private Double winningAmount;
    @SerializedName("top_up_wallet_balance")
    @Expose
    private Double topUpWalletBalance;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getPin() {
        return pin;
    }

    public void setPin(Double pin) {
        this.pin = pin;
    }

    public Double getDice1() {
        return dice1;
    }

    public void setDice1(Double dice1) {
        this.dice1 = dice1;
    }

    public Double getDice2() {
        return dice2;
    }

    public void setDice2(Double dice2) {
        this.dice2 = dice2;
    }

    public Double getDiceSum() {
        return diceSum;
    }

    public void setDiceSum(Double diceSum) {
        this.diceSum = diceSum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getWinningAmount() {
        return winningAmount;
    }

    public void setWinningAmount(Double winningAmount) {
        this.winningAmount = winningAmount;
    }

    public Double getTopUpWalletBalance() {
        return topUpWalletBalance;
    }

    public void setTopUpWalletBalance(Double topUpWalletBalance) {
        this.topUpWalletBalance = topUpWalletBalance;
    }
}
