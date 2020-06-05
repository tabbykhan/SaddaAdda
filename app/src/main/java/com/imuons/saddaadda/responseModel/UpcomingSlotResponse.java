package com.imuons.saddaadda.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.imuons.saddaadda.DataModel.UpcomingSlotData;

import java.util.ArrayList;

public class UpcomingSlotResponse {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<UpcomingSlotData> data = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<UpcomingSlotData> getData() {
        return data;
    }

    public void setData(ArrayList<UpcomingSlotData> data) {
        this.data = data;
    }

}
