package com.fanonx.chatbot_demo.models;

import androidx.annotation.NonNull;
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
    @SerializedName("time_stamp")
    private String timeStamp;
    @Embedded
    @SerializedName("sensor_data")
    private ActivitySensorData sensorData;
    private String sensorName;

    public ActivityModel(String timeStamp, ActivitySensorData sensorData, String sensorName) {
        this.timeStamp = timeStamp;
        this.sensorData = sensorData;
        this.sensorName = sensorName;
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


    @NonNull
    @Override
    public String toString() {
        return "ActivityModel{" +
                "id=" + id +
                ", timeStamp='" + timeStamp + '\'' +
                ", sensorData=" + sensorData +
                ", sensorName='" + sensorName + '\'' +
                '}';
    }
}
