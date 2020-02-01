package com.example.dyco;

public class EventColor {
    String colorR;
    String timeInMillsR;

    public EventColor(String colorR, String timeInMillsR) {
        this.colorR = colorR;
        this.timeInMillsR = timeInMillsR;
    }

    public String getColorR() {
        return colorR;
    }

    public void setColorR(String colorR) {
        this.colorR = colorR;
    }

    public String getTimeInMillsR() {
        return timeInMillsR;
    }

    public void setTimeInMillsR(String timeInMillsR) {
        this.timeInMillsR = timeInMillsR;
    }
}
