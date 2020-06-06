package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionDataModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("tranid")
    @Expose
    private String tranid;
    @SerializedName("transaction_hash")
    @Expose
    private Object transactionHash;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("attachment")
    @Expose
    private String attachment;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("a1")
    @Expose
    private String a1;
    private final static long serialVersionUID = -2505878171529639878L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTranid() {
        return tranid;
    }

    public void setTranid(String tranid) {
        this.tranid = tranid;
    }

    public Object getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(Object transactionHash) {
        this.transactionHash = transactionHash;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;

    }
}
