package com.example.dyco;

public class QrCodeData {

    String userUid,timeSpan,random;

    public QrCodeData(String userUid, String timeSpan, String random) {
        this.userUid = userUid;
        this.timeSpan = timeSpan;
        this.random = random;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(String timeSpan) {
        this.timeSpan = timeSpan;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }
}
