package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SathKaDamData {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("pin")
    @Expose
    private Integer pin;
    @SerializedName("dice1")
    @Expose
    private Integer dice1;
    @SerializedName("dice2")
    @Expose
    private Integer dice2;
    @SerializedName("dice_sum")
    @Expose
    private Integer diceSum;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("winning_amount")
    @Expose
    private Integer winningAmount;
    @SerializedName("top_up_wallet_balance")
    @Expose
    private Integer topUpWalletBalance;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public Integer getDice1() {
        return dice1;
    }

    public void setDice1(Integer dice1) {
        this.dice1 = dice1;
    }

    public Integer getDice2() {
        return dice2;
    }

    public void setDice2(Integer dice2) {
        this.dice2 = dice2;
    }

    public Integer getDiceSum() {
        return diceSum;
    }

    public void setDiceSum(Integer diceSum) {
        this.diceSum = diceSum;
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

    public Integer getTopUpWalletBalance() {
        return topUpWalletBalance;
    }

    public void setTopUpWalletBalance(Integer topUpWalletBalance) {
        this.topUpWalletBalance = topUpWalletBalance;
    }
}
