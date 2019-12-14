package com.fanonx.chatbot_demo.datahandler.firehandler;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.fanonx.chatbot_demo.commons.Constants;
import com.fanonx.chatbot_demo.models.ActivfitModel;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireBaseDataHandler {
    /**
     * Firebase handler class
     * responsible for inserting given data into firebase
     * and executing search into database
     */
    private static final String TAG = FireBaseDataHandler.class.getSimpleName();

    private Gson gson = new Gson();
    // Access a Cloud Fire store instance from your Activity
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    /***
     * method to parse the json file and insert into firebase
     */
    public void parseJson(Context appContext, String jsonFilePath, String className) throws IOException {
        // use inputStream to get the file path
        InputStream is = appContext.getAssets().open(jsonFilePath);
        // read the JSON file
        try (Reader reader = new InputStreamReader(is)) {
            // convert the json file into an object
            if (className.compareTo(Constants.ACTIVFIT_CLASSNAME) == 0) {
                // use gson to map it to the model
                Type activListType = new TypeToken<ArrayList<ActivfitModel>>(){}.getType();
                List<ActivfitModel> activfitModels = gson.fromJson(reader, activListType);

                Log.i(TAG, activfitModels.get(0).toString());
                // write the data as a batch
                WriteBatch batch = db.batch();
                // loop over the activFitModels
                for (int i = 0; i < activfitModels.size() && i < 500; i++) {
                    ActivfitModel model = activfitModels.get(i);
                    // structure the object properly since firebase cannot handle nested data
                    Map<String, Object> data = new HashMap<>();
                    Map<String, Object> sensorData = new HashMap<>();
                    sensorData.put("duration", model.getSensorData().getDuration());
                    sensorData.put("activity", model.getSensorData().getActivity());
                    Map<String, Object> timestampMap = new HashMap<>();
                    timestampMap.put("startTime", model.getTimeStamp().getStartTime());
                    timestampMap.put("endTime", model.getTimeStamp().getEndTime());

                    data.put("sensorData", sensorData);
                    data.put("timestamp", timestampMap);
                    data.put("sensorName", model.getSensorName());
                    // reference the document from the collection
                    DocumentReference ref = db.collection("activfit").document();
                    // add the doc ref to the batch
                    batch.set(ref, data);
                }

                // execute on all Activfit models the insertion into the database.
                batch.commit().addOnCompleteListener(task -> {
                    Log.i(TAG, String.valueOf(task.getException()));
                    if (task.isSuccessful()) {
                        Toast.makeText(appContext, "SUCCESS", Toast.LENGTH_LONG).show();
                    }
                     else {
                        Toast.makeText(appContext, "FAILURE", Toast.LENGTH_LONG).show();
                    }
                });
            }
        } catch (Exception e) {
            Log.d(TAG, "Error: " + e.getMessage());
        }
    }
}
