package com.fanonx.chatbot_demo.datahandler;

import android.content.Context;
import android.util.Log;

import com.fanonx.chatbot_demo.commons.Constants;
import com.fanonx.chatbot_demo.datahandler.firehandler.FireBaseDataHandler;
import com.fanonx.chatbot_demo.datahandler.sqlDataHandler.RoomDataHandler;

import java.io.IOException;

public class DataSetupFacade {
    /**
     * Class to just to load data
     * */
    private static final String TAG = DataSetupFacade.class.getSimpleName();
    private FireBaseDataHandler fireBaseDataHandler = new FireBaseDataHandler();

    public void setupData(Context appContext) {
        // insert the data into fire base
        Log.i(TAG, "Inserting the data into firebase.");
        fireBaseDataHandler.parseJson(appContext, Constants.activFitPath);
        fireBaseDataHandler.parseActivity(appContext, Constants.activityPath);
        fireBaseDataHandler.parseHeartRate(appContext, Constants.heartRatePath);

        Log.i(TAG, "Inserting data into sql lite");
        // inserting the activfit data into sql lite db on a single thread.
        new Thread(() -> {
            try {
                RoomDataHandler.parseJSON(appContext, Constants.activFitPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // inserting the activity data into sql lite db on a new thread.
        new Thread(() -> {
            RoomDataHandler.parseActivity(appContext, Constants.activityPath);
        }).start();

        // inserting the heart rate data into sql lite db on a new thread.
        new Thread(() -> {
            RoomDataHandler.parseHeartRate(appContext, Constants.heartRatePath);
        }).start();
    }
}
