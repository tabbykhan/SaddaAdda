package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaddaxReportDataModel {
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("slot_no")
    @Expose
    private Integer slotNo;
    @SerializedName("date")
    @Expose
    private String date;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(Integer slotNo) {
        this.slotNo = slotNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
