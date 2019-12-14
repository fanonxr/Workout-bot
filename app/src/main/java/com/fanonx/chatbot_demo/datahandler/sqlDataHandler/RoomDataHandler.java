package com.fanonx.chatbot_demo.datahandler.sqlDataHandler;

import android.content.Context;
import android.util.Log;

import com.fanonx.chatbot_demo.database.ActivfitModelDao;
import com.fanonx.chatbot_demo.database.AppDatabase;
import com.fanonx.chatbot_demo.models.ActivfitModel;
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
    private static final String TAG = RoomDataHandler.class.getSimpleName();
    private static Gson gson = new Gson();

    private static ActivfitModelDao getActivfitModelDao(Context appContext)  {
        AppDatabase appDatabase = AppDatabase.getInstance(appContext);
        return appDatabase.activfitModelDao();
    }

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

    public static List<ActivfitModel> getAllActivfitModels(Context appContext)  {
        ActivfitModelDao dao = getActivfitModelDao(appContext);
        return dao.getAllModels();
    }
}
