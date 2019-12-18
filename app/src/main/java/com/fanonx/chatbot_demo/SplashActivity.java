package com.fanonx.chatbot_demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fanonx.chatbot_demo.datahandler.firehandler.FireBaseDataHandler;
import com.fanonx.chatbot_demo.datahandler.sqlDataHandler.RoomDataHandler;
import com.fanonx.chatbot_demo.models.ActivfitModel;
import com.fanonx.chatbot_demo.models.ActivityModel;
import com.fanonx.chatbot_demo.models.HeartRateModel;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    FireBaseDataHandler fireBaseDataHandler = new FireBaseDataHandler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(() -> {
            List<HeartRateModel> heartRateModels = RoomDataHandler.getAllHeartRateModels(getApplicationContext());
            List<ActivfitModel> activfitModels = RoomDataHandler.getAllActivfitModels(getApplicationContext());
            List<ActivityModel> activityModels = RoomDataHandler.getAllActivityModels(getApplicationContext());
            fireBaseDataHandler.getHeartRateItems(list -> {

            });
            ((App)getApplication()).setHeartRateModels(heartRateModels);
            ((App)getApplication()).setActivfitModels(activfitModels);
            ((App)getApplication()).setActivityModels(activityModels);
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }).start();
    }
}
