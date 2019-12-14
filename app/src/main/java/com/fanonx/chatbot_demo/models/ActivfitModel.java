package com.fanonx.chatbot_demo.models;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "activfit")
public class ActivfitModel {
    /**
     * Activ fit model to be inserted into firebase.
     * */
    @PrimaryKey(autoGenerate = true)
    private int id;
    @SerializedName("sensor_data")
    @Embedded
    private SensorData sensorData;
    @SerializedName("sensor_name")
    private String sensorName;
    @SerializedName("timestamp")
    @Embedded
    private TimeStamp timeStamp;

    public ActivfitModel(SensorData sensorData, String sensorName, TimeStamp timeStamp) {
        this.sensorData = sensorData;
        this.sensorName = sensorName;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSensorName() {
        return sensorName;
    }

    public SensorData getSensorData() {
        return sensorData;
    }

    public void setSensorData(SensorData sensorData) {
        this.sensorData = sensorData;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public TimeStamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(TimeStamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    @NonNull
    @Override
    public String toString() {
        return "ActivfitModel{" +
                "sensorData=" + sensorData +
                ", sensorName='" + sensorName + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
