package com.imuons.saddaadda.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageDataModel {
    @SerializedName("left")
    @Expose
    private LeftMessageModel left;
    @SerializedName("right")
    @Expose
    private RighMessageModel right;
    private final static long serialVersionUID = -4436422158239321347L;
    private String showtime;

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }


  public   boolean isIs_title() {
        return is_title;
    }

   public void setIs_title(boolean is_title) {
        this.is_title = is_title;
    }

    boolean is_title=false;



    public LeftMessageModel getLeft() {
        return left;
    }

    public void setLeft(LeftMessageModel left) {
        this.left = left;
    }

    public RighMessageModel getRight() {
        return right;
    }

    public void setRight(RighMessageModel right) {
        this.right = right;
    }
}
