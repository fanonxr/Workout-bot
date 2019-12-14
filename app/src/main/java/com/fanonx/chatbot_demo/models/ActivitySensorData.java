package com.fanonx.chatbot_demo.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class ActivitySensorData {
    @SerializedName("step_delta")
    private int stepDelta;
    @SerializedName("step_counts")
    private int stepCounts;

    public ActivitySensorData(int stepDelta, int stepCounts) {
        this.stepDelta = stepDelta;
        this.stepCounts = stepCounts;
    }

    public int getStepDelta() {
        return stepDelta;
    }

    public void setStepDelta(int stepDelta) {
        this.stepDelta = stepDelta;
    }

    public int getStepCounts() {
        return stepCounts;
    }

    public void setStepCounts(int stepCounts) {
        this.stepCounts = stepCounts;
    }

    @NonNull
    @Override
    public String toString() {
        return "ActivitySensorData{" +
                "stepDelta=" + stepDelta +
                ", stepCounts=" + stepCounts +
                '}';
    }
}
