package com.fanonx.chatbot_demo;

import android.app.Application;

import com.fanonx.chatbot_demo.models.ActivfitModel;
import com.fanonx.chatbot_demo.models.ActivityModel;
import com.fanonx.chatbot_demo.models.HeartRateModel;

import java.util.List;

public class App extends Application {

    private List<HeartRateModel> mHeartRateModels;
    private List<ActivfitModel> mActivfitModels;
    private List<ActivityModel> mActivityModels;

    public void setHeartRateModels(List<HeartRateModel> heartRateModels) {
        mHeartRateModels = heartRateModels;
    }

    public void setActivfitModels(List<ActivfitModel> activfitModels) {
        mActivfitModels = activfitModels;
    }

    public void setActivityModels(List<ActivityModel> activityModels) {
        mActivityModels = activityModels;
    }

    public List<HeartRateModel> getHeartRateModels() {
        return mHeartRateModels;
    }

    public List<ActivfitModel> getActivfitModels() {
        return mActivfitModels;
    }

    public List<ActivityModel> getActivityModels() {
        return mActivityModels;
    }



}
