package com.fanonx.chatbot_demo.models;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "heartrate")
public class HeartRateModel {
    /**
     * heart Rate Model
     * */
    @PrimaryKey(autoGenerate = true)
    private int id;
    @Embedded
    @SerializedName("sensor_data")
    private HeartRateSensorData sensorData;
    @SerializedName("sensor_name")
    private String sensorName;
    @SerializedName("timestamp")
    private String timestamp;

    public HeartRateModel(HeartRateSensorData sensorData, String sensorName, String timestamp) {
        this.sensorData = sensorData;
        this.sensorName = sensorName;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HeartRateSensorData getSensorData() {
        return sensorData;
    }

    public void setSensorData(HeartRateSensorData sensorData) {
        this.sensorData = sensorData;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @NonNull
    @Override
    public String toString() {
        return "HeartRateModel{" +
                "id=" + id +
                ", sensorData=" + sensorData +
                ", sensorName='" + sensorName + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
