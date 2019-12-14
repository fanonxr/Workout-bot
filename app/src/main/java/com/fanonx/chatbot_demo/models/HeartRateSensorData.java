package com.fanonx.chatbot_demo.models;

import androidx.annotation.NonNull;

public class HeartRateSensorData {
    private int bpm;

    public HeartRateSensorData(int bpm) {
        this.bpm = bpm;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    @NonNull
    @Override
    public String toString() {
        return "HeartRateSensorData{" +
                "bpm=" + bpm +
                '}';
    }
}
