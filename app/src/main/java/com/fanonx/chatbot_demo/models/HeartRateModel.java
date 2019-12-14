package com.fanonx.chatbot_demo.models;

import java.util.Map;

public class HeartRateModel {
    /**
     * heart Rate Model
     * */
    private Map<String, Integer> sensorData;
    private String sensorName;
    private String timestamp;

    public HeartRateModel(Map<String, Integer> sensorData, String sensorName, String timestamp) {
        this.sensorData = sensorData;
        this.sensorName = sensorName;
        this.timestamp = timestamp;
    }

    public Map<String, Integer> getSensorData() {
        return sensorData;
    }

    public String getSensorName() {
        return sensorName;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
