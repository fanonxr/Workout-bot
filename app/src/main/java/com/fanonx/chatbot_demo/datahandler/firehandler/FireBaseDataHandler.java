package com.fanonx.chatbot_demo.datahandler.firehandler;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.fanonx.chatbot_demo.models.ActivfitModel;
import com.fanonx.chatbot_demo.models.ActivityModel;
import com.fanonx.chatbot_demo.models.HeartRateModel;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

    private static Gson gson = new Gson();
    // Access a Cloud Fire store instance from your Activity
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    /***
     * method to parse the json file and insert into firebase.
     */
    public void parseJson(Context appContext, String jsonFilePath) {
        try  {
            // use inputStream to get the file path
            InputStream is = appContext.getAssets().open(jsonFilePath);
            // read the JSON file
            // convert the json file into an object
            Reader reader = new InputStreamReader(is);
            // use gson to map it to the model
            Type activListType = new TypeToken<ArrayList<ActivfitModel>>() {}.getType();
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
                } else {
                    Toast.makeText(appContext, "FAILURE", Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception e) {
            Log.d(TAG, "Error: " + e.getMessage());
        }
    }

    /**
     * method to transfer json data from activity to firebase.
     */
    public void parseActivity(Context appContext, String jsonFilePath) {
        try {
            // use inputStream to get the file path
            InputStream is = appContext.getAssets().open(jsonFilePath);
            Reader reader = new InputStreamReader(is);

            // use gson to map it to the model
            Type activityListType = new TypeToken<ArrayList<ActivityModel>>() {
            }.getType();
            List<ActivityModel> activityModels = gson.fromJson(reader, activityListType);

            Log.i(TAG, activityModels.get(0).toString());
            // write the data as a batch
            WriteBatch batch = db.batch();
            // loop over the activFitModels
            for (int i = 0; i < activityModels.size() && i < 500; i++) {
                // get the model object
                ActivityModel activityModel = activityModels.get(i);
                // structure the object properly since fire base cannot handle nested data
                Map<String, Object> data = new HashMap<>();
                Map<String, Object> sensorData = new HashMap<>();

                sensorData.put("step_delta", activityModel.getSensorData().getStepDelta());
                sensorData.put("step_counts", activityModel.getSensorData().getStepCounts());

                data.put("sensor_data", sensorData);
                data.put("sensor_name", activityModel.getSensorName());
                data.put("timestamp", activityModel.getTimeStamp());

                // reference the document from the collection
                DocumentReference ref = db.collection("activity").document();
                // add the doc ref to the batch
                batch.set(ref, data);
            }

            // execute on all Activity models the insertion into the database.
            batch.commit().addOnCompleteListener(task -> {
                Log.i(TAG, String.valueOf(task.getException()));
                if (task.isSuccessful()) {
                    Toast.makeText(appContext, "SUCCESS", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(appContext, "FAILURE", Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception e) {
            Log.i(TAG, "Error: " + e.getMessage());
        }
    }

    public void parseHeartRate(Context appContext, String jsonFilePath) {
        try {
            // use inputStream to get the file path
            InputStream is = appContext.getAssets().open(jsonFilePath);
            Reader reader = new InputStreamReader(is);

            // use gson to map it to the model
            Type heartRateListType = new TypeToken<ArrayList<HeartRateModel>>() {
            }.getType();
            List<HeartRateModel> heartRateModels = gson.fromJson(reader, heartRateListType);

            Log.i(TAG, heartRateModels.get(0).toString());
            // write the data as a batch
            WriteBatch batch = db.batch();
            // loop over the heart rate models
            for (int i = 0; i < heartRateModels.size() && i < 500; i++) {
                // get the model object
                HeartRateModel heartRateModel = heartRateModels.get(i);
                // structure the object properly since fire base cannot handle nested data
                Map<String, Object> data = new HashMap<>();
                Map<String, Object> sensorData = new HashMap<>();

                sensorData.put("bpm", heartRateModel.getSensorData().getBpm());

                data.put("sensor_data", sensorData);
                data.put("sensor_name", heartRateModel.getSensorName());
                data.put("timestamp", heartRateModel.getTimestamp());

                // reference the document from the collection
                DocumentReference ref = db.collection("heartrate").document();
                // add the doc ref to the batch
                batch.set(ref, data);
            }

            // execute on all Activity models the insertion into the database.
            batch.commit().addOnCompleteListener(task -> {
                Log.i(TAG, String.valueOf(task.getException()));
                if (task.isSuccessful()) {
                    Toast.makeText(appContext, "SUCCESS", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(appContext, "FAILURE", Toast.LENGTH_LONG).show();
                }
            });

        } catch (Exception e) {
            Log.i(TAG, "Error: " + e.getMessage());
        }
    }

    /**
     * Method to get fire data from firebase
     * */
    public void getHeartRateItems() {
//        db.collection("heartrate").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    if (task.getResult() != null) {
//                        List<HeartRateModel> heartRateModels = task.getResult().toObjects(HeartRateModel.class);
//                        for (HeartRateModel model: heartRateModels) {
//                            Log.i(TAG, model.getTimestamp());
//                        }
//                    }
//                } else {
//                    Log.i(TAG, "Error: " ,task.getException());
//                }
//            }
//        });
    }

}
