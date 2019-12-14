package com.fanonx.chatbot_demo;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fanonx.chatbot_demo.commons.Constants;
import com.fanonx.chatbot_demo.datahandler.sqlDataHandler.RoomDataHandler;
import com.fanonx.chatbot_demo.models.ActivfitModel;
import com.fanonx.chatbot_demo.models.ActivityModel;
import com.fanonx.chatbot_demo.models.HeartRateModel;
import com.fanonx.chatbot_demo.searcher.text.TextSearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /**
     * fields to be created on start up of app.
     * */
    EditText userInput;
    RecyclerView recyclerView;
    List<ResponseMessage> responseMessageList;
    MessageAdapter messageAdapter;
    TextSearch textSearcher = new TextSearch();
    List<ActivfitModel> activfitModels;
    List<ActivityModel> activityModels;
    List<HeartRateModel> heartRateModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        try {
//            FireBaseDataHandler.parseJson(getApplicationContext(), Constants.activFitPath, Constants.ACTIVFIT_CLASSNAME);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // inserting the activfit data into sql lite db on a single thread.
        new Thread(() -> {
            try {
                RoomDataHandler.parseJSON(getApplicationContext(), Constants.activFitPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            activfitModels = RoomDataHandler.getAllActivfitModels(getApplicationContext());
            Log.i("MainActivityRoom", "activfit list size: " + activfitModels.size() + "");
        }).start();

        // inserting the activity data into sql lite db on a new thread.
        new Thread(() -> {
            RoomDataHandler.parseActivity(getApplicationContext(), Constants.activityPath);
            activityModels = RoomDataHandler.getAllActivityModels(getApplicationContext());
            Log.i("MainActivityRoom", "activity list size: " + activityModels.size() + "");
        }).start();

        // inserting the heart rate data into sql lite db on a new thread.
        new Thread(() -> {
            RoomDataHandler.parseHeartRate(getApplicationContext(), Constants.heartRatePath);
            heartRateModels = RoomDataHandler.getAllHeartRateModels(getApplicationContext());
            Log.i("MainActivityRoom", "heart rate size: " + heartRateModels.size() + "");
        }).start();

        userInput = findViewById(R.id.userInput);
        recyclerView = findViewById(R.id.conversation);

        responseMessageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(responseMessageList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(messageAdapter);

        userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    // sent from the user
                    ResponseMessage userMessage = new ResponseMessage(userInput.getText().toString(), true);
                    responseMessageList.add(userMessage);

                    // sent from the chat bot
                    ResponseMessage botMessage = new ResponseMessage("from chatbot", false);
                    responseMessageList.add(botMessage);

                    // notify the view that the data has changed => update the view
                    messageAdapter.notifyDataSetChanged();

                    // automatically scroll to the position of the text
                    if (!isVisible()) {
                        recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                    }
                }
                return true;
            }
        });
    }

    /**
     * method to check if the element at the bottom of the page is visible
     * @ return true or false based on visibility
     * */
    public boolean isVisible() {
        try {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int positionOfLastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
            int itemCount = recyclerView.getAdapter().getItemCount();
            return (positionOfLastVisibleItem >= itemCount);
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return false;
    }
}
