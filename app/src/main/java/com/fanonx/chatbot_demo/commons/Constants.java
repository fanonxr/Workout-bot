package com.fanonx.chatbot_demo.commons;

import ai.api.android.AIConfiguration;

public class Constants {

    public static final String activFitPath = "json-data/activfit.json";
    public static final String activityPath = "json-data/activity.json";
    public static final String heartRatePath = "json-data/heartrate.json";
    public static final String ACTIVFIT_CLASSNAME = "";

    private static final String ACCESS_TOKEN = "45b3b485a95b4399bd2e1d6df3406353";

    public static AIConfiguration config = new AIConfiguration(ACCESS_TOKEN, AIConfiguration.SupportedLanguages.English, AIConfiguration.RecognitionEngine.System);
}
