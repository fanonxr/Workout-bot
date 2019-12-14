package com.fanonx.chatbot_demo.models;

import java.util.Map;

public class ActivityModel {
    /**
     * Activity Model
     * */
    private String timeStamp;
    private Map<String, String> sensorData;
    private String sensorName;
    private String timestamp;

    public ActivityModel(String timeStamp, Map<String, String> sensorData, String sensorName, String timestamp) {
        this.timeStamp = timeStamp;
        this.sensorData = sensorData;
        this.sensorName = sensorName;
        this.timestamp = timestamp;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public Map<String, String> getSensorData() {
        return sensorData;
    }

    public String getSensorName() {
        return sensorName;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
