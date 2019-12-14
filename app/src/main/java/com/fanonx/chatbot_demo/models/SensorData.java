package com.fanonx.chatbot_demo.models;

import androidx.annotation.NonNull;

public class SensorData {
    private Long duration;
    private String activity;

    public SensorData(Long duration, String activity) {
        this.duration = duration;
        this.activity = activity;
    }

    public Long getDuration() {
        return duration;
    }

    public String getActivity() {
        return activity;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public String toString() {
        return "SensorData{" +
                "duration=" + duration +
                ", activity='" + activity + '\'' +
                '}';
    }
}
