package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchChatDataModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("from_user_id")
    @Expose
    private Integer fromUserId;
    @SerializedName("to_user_id")
    @Expose
    private Integer toUserId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("attachment")
    @Expose
    private String attachment;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("fromuser_id")
    @Expose
    private String fromuserId;
    @SerializedName("touser_id")
    @Expose
    private String touserId;
    @SerializedName("timing")
    @Expose
    private String timing;
    @SerializedName("current_timing")
    @Expose
    private String currentTiming;
    @SerializedName("files")
    @Expose
    private List<String> files = null;
    @SerializedName("position")
    @Expose
    private String position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFromuserId() {
        return fromuserId;
    }

    public void setFromuserId(String fromuserId) {
        this.fromuserId = fromuserId;
    }

    public String getTouserId() {
        return touserId;
    }

    public void setTouserId(String touserId) {
        this.touserId = touserId;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getCurrentTiming() {
        return currentTiming;
    }

    public void setCurrentTiming(String currentTiming) {
        this.currentTiming = currentTiming;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


}
