package com.imuons.saddaadda.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("pin_passwd")
    @Expose
    private String pinPasswd;
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("session_id")
    @Expose
    private Integer sessionId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPinPasswd() {
        return pinPasswd;
    }

    public void setPinPasswd(String pinPasswd) {
        this.pinPasswd = pinPasswd;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }
}
