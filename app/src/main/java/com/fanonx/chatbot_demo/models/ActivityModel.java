package com.fanonx.chatbot_demo.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "activity")
public class ActivityModel {
    /**
     * Activity Model
     * */
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String timeStamp;
    @Embedded
    @SerializedName("sensor_data")
    private ActivitySensorData sensorData;
    private String sensorName;
    private String timestamp;

    public ActivityModel(String timeStamp, ActivitySensorData sensorData, String sensorName, String timestamp) {
        this.timeStamp = timeStamp;
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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ActivitySensorData getSensorData() {
        return sensorData;
    }

    public void setSensorData(ActivitySensorData sensorData) {
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

    @Override
    public String toString() {
        return "ActivityModel{" +
                "id=" + id +
                ", timeStamp='" + timeStamp + '\'' +
                ", sensorData=" + sensorData +
                ", sensorName='" + sensorName + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
