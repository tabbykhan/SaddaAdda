package com.imuons.saddaadda.DataModel;

public class NotificationListObject {
    int slotNo,id;
    String timeStap;
    boolean isRemind;

    public int getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(int slotNo) {
        this.slotNo = slotNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeStap() {
        return timeStap;
    }

    public void setTimeStap(String timeStap) {
        this.timeStap = timeStap;
    }

    public boolean isRemind() {
        return isRemind;
    }

    public void setRemind(boolean remind) {
        isRemind = remind;
    }

    public NotificationListObject(int slotNo, int id, String timeStap, boolean isRemind) {
        this.slotNo = slotNo;
        this.id = id;
        this.timeStap = timeStap;
        this.isRemind = isRemind;
    }
}
