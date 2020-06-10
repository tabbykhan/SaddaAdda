package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShatakProductRecord {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("bvalue")
    @Expose
    private Integer bvalue;
    @SerializedName("direct_income")
    @Expose
    private Integer directIncome;
    @SerializedName("winning_amount")
    @Expose
    private Integer winningAmount;
    @SerializedName("hash_rate")
    @Expose
    private String hashRate;
    @SerializedName("min_hash")
    @Expose
    private Integer minHash;
    @SerializedName("max_hash")
    @Expose
    private Integer maxHash;
    @SerializedName("roi")
    @Expose
    private Integer roi;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("daily_profit")
    @Expose
    private Integer dailyProfit;
    @SerializedName("total_profit")
    @Expose
    private Integer totalProfit;
    @SerializedName("level_roi")
    @Expose
    private Integer levelRoi;
    @SerializedName("duration_month")
    @Expose
    private Integer durationMonth;
    @SerializedName("capping")
    @Expose
    private Integer capping;
    @SerializedName("binary")
    @Expose
    private Integer binary;
    @SerializedName("date_diff")
    @Expose
    private Integer dateDiff;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("currency_code")
    @Expose
    private Object currencyCode;
    @SerializedName("management_fee")
    @Expose
    private Integer managementFee;
    @SerializedName("consumption_charge")
    @Expose
    private Integer consumptionCharge;
    @SerializedName("conversion_rate")
    @Expose
    private Integer conversionRate;
    @SerializedName("user_show_status")
    @Expose
    private String userShowStatus;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("ref_product_id")
    @Expose
    private Integer refProductId;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    private final static long serialVersionUID = -7281212713559665144L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getBvalue() {
        return bvalue;
    }

    public void setBvalue(Integer bvalue) {
        this.bvalue = bvalue;
    }

    public Integer getDirectIncome() {
        return directIncome;
    }

    public void setDirectIncome(Integer directIncome) {
        this.directIncome = directIncome;
    }

    public Integer getWinningAmount() {
        return winningAmount;
    }

    public void setWinningAmount(Integer winningAmount) {
        this.winningAmount = winningAmount;
    }

    public String getHashRate() {
        return hashRate;
    }

    public void setHashRate(String hashRate) {
        this.hashRate = hashRate;
    }

    public Integer getMinHash() {
        return minHash;
    }

    public void setMinHash(Integer minHash) {
        this.minHash = minHash;
    }

    public Integer getMaxHash() {
        return maxHash;
    }

    public void setMaxHash(Integer maxHash) {
        this.maxHash = maxHash;
    }

    public Integer getRoi() {
        return roi;
    }

    public void setRoi(Integer roi) {
        this.roi = roi;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDailyProfit() {
        return dailyProfit;
    }

    public void setDailyProfit(Integer dailyProfit) {
        this.dailyProfit = dailyProfit;
    }

    public Integer getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Integer totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Integer getLevelRoi() {
        return levelRoi;
    }

    public void setLevelRoi(Integer levelRoi) {
        this.levelRoi = levelRoi;
    }

    public Integer getDurationMonth() {
        return durationMonth;
    }

    public void setDurationMonth(Integer durationMonth) {
        this.durationMonth = durationMonth;
    }

    public Integer getCapping() {
        return capping;
    }

    public void setCapping(Integer capping) {
        this.capping = capping;
    }

    public Integer getBinary() {
        return binary;
    }

    public void setBinary(Integer binary) {
        this.binary = binary;
    }

    public Integer getDateDiff() {
        return dateDiff;
    }

    public void setDateDiff(Integer dateDiff) {
        this.dateDiff = dateDiff;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Object currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Integer managementFee) {
        this.managementFee = managementFee;
    }

    public Integer getConsumptionCharge() {
        return consumptionCharge;
    }

    public void setConsumptionCharge(Integer consumptionCharge) {
        this.consumptionCharge = consumptionCharge;
    }

    public Integer getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Integer conversionRate) {
        this.conversionRate = conversionRate;
    }

    public String getUserShowStatus() {
        return userShowStatus;
    }

    public void setUserShowStatus(String userShowStatus) {
        this.userShowStatus = userShowStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRefProductId() {
        return refProductId;
    }

    public void setRefProductId(Integer refProductId) {
        this.refProductId = refProductId;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
