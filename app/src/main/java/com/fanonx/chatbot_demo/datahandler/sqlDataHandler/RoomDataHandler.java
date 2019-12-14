package com.fanonx.chatbot_demo.datahandler.sqlDataHandler;

import android.content.Context;
import android.util.Log;

import com.fanonx.chatbot_demo.database.ActivfitModelDao;
import com.fanonx.chatbot_demo.database.ActivityModelDAO;
import com.fanonx.chatbot_demo.database.AppDatabase;
import com.fanonx.chatbot_demo.database.HeartRateDao;
import com.fanonx.chatbot_demo.models.ActivfitModel;
import com.fanonx.chatbot_demo.models.ActivityModel;
import com.fanonx.chatbot_demo.models.HeartRateModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RoomDataHandler {
    /**
     * Room Handler class to manage SQL operations on various models
     * */
    private static final String TAG = RoomDataHandler.class.getSimpleName();
    private static Gson gson = new Gson();

    /**
     * method to get the ActivFitModelDao
     * */
    private static ActivfitModelDao getActivfitModelDao(Context appContext)  {
        // get the instance of the app
        AppDatabase appDatabase = AppDatabase.getInstance(appContext);
        return appDatabase.activfitModelDao();
    }

    /**
     * method to get the ActivityModelDao.
     * */
    private static ActivityModelDAO getActivityModelDao(Context appContext) {
        AppDatabase appDatabase = AppDatabase.getInstance(appContext);
        return appDatabase.activityModelDAO();
    }

    /**
     * Method to invoke heartrateDao.
     * */
    private static HeartRateDao getHeartRateModelDao(Context appContext) {
        AppDatabase appDatabase = AppDatabase.getInstance(appContext);
        return  appDatabase.heartRateDao();
    }

    /**
     * Method to parseJson file and insert it into the database.
     * @param appContext the context of the app
     * @param jsonFilePath the file path to the json file
     * */
    public static void parseJSON(Context appContext, String jsonFilePath) throws IOException {
        InputStream is = appContext.getAssets().open(jsonFilePath);
        // read the JSON file
        try (Reader reader = new InputStreamReader(is)) {
            Type activListType = new TypeToken<ArrayList<ActivfitModel>>(){}.getType();
            List<ActivfitModel> activfitModels = gson.fromJson(reader, activListType);

            ActivfitModelDao dao = getActivfitModelDao(appContext);
            dao.insert(activfitModels);

        } catch (Exception e) {
            Log.d(TAG, "Error: " + e.getMessage());
        }
    }

    /**
     * Method to get a list of ActFitModel objects in java.
     * @param appContext the context of the app
     * */
    public static List<ActivfitModel> getAllActivfitModels(Context appContext)  {
        ActivfitModelDao dao = getActivfitModelDao(appContext);
        return dao.getAllModels();
    }

    /**
     * Method to parse the activity json file and insert it into the database
     * */
    public static void parseActivity(Context appContext, String jsonFilePath) {
        // read the json file
        try {
            InputStream is = appContext.getAssets().open(jsonFilePath);
            Reader reader = new InputStreamReader(is);
            // structure the type for gson
            Type activityListType = new TypeToken<ArrayList<ActivityModel>>(){}.getType();
            List<ActivityModel> activityModels = gson.fromJson(reader, activityListType);

            // use the DAO to insert the data into the database
            ActivityModelDAO dao = getActivityModelDao(appContext);
            dao.insert(activityModels);

        } catch (Exception e) {
            Log.i(TAG, "Error:" + e.getMessage());
        }
    }

    /**
     * Method to get a list of ActivityModels objects
     * @param appContext the context of the app
     * */
    public static List<ActivityModel> getAllActivityModels(Context appContext)  {
        ActivityModelDAO dao = getActivityModelDao(appContext);
        return dao.getAllModels();
    }

    /**
     * Method to parse the HeartRate json file into the sql database.
     * */
    public static void parseHeartRate(Context appContext, String jsonFilePath) {
        // read the json file
        try {
            InputStream is = appContext.getAssets().open(jsonFilePath);
            Reader reader = new InputStreamReader(is);
            // structure the type for gson
            Type heartRateListType = new TypeToken<ArrayList<HeartRateModel>>(){}.getType();
            List<HeartRateModel> heartRateModels = gson.fromJson(reader, heartRateListType);

            // use the DAO to insert the data into the database
            HeartRateDao dao = getHeartRateModelDao(appContext);
            dao.insert(heartRateModels);

        } catch (Exception e) {
            Log.i(TAG, "Error:" + e.getMessage());
        }
    }

    /**
     * Method to get a list of Heart rate objects
     * @param appContext the context of the app
     * */
    public static List<HeartRateModel> getAllHeartRateModels(Context appContext)  {
        HeartRateDao dao = getHeartRateModelDao(appContext);
        return dao.getAllModels();
    }

}
