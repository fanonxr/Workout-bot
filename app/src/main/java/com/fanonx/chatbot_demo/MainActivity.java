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

import com.fanonx.chatbot_demo.chatbot.ChatBot;
import com.fanonx.chatbot_demo.commons.Constants;
import com.fanonx.chatbot_demo.datahandler.DataSetupFacade;
import com.fanonx.chatbot_demo.datahandler.firehandler.FireBaseDataHandler;
import com.fanonx.chatbot_demo.datahandler.sqlDataHandler.RoomDataHandler;
import com.fanonx.chatbot_demo.models.HeartRateModel;

import java.util.ArrayList;
import java.util.List;

import ai.api.AIListener;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class MainActivity extends AppCompatActivity implements AIListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    /**
     * fields to be created on start up of app.
     */
    EditText userInput;
    RecyclerView recyclerView;
    List<ResponseMessage> responseMessageList;
    MessageAdapter messageAdapter;
    DataSetupFacade facade = new DataSetupFacade();
    ChatBot chatBot = new ChatBot();
    List<HeartRateModel> listOfHeartRateModels;
    FireBaseDataHandler fireBaseDataHandler = new FireBaseDataHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // configuration for dialog flow
        AIService aiService = AIService.getService(this, Constants.config);
        aiService.setListener(this);

        // set up data on no sql and fire base -> comment this line out after you ran it once
        // facade.setupData(getApplicationContext());

        // fireBaseDataHandler.getHeartRateItems();

        // thread to get the heart rates from the SQL database
        new Thread(() -> {
           listOfHeartRateModels = RoomDataHandler.getAllHeartRateModels(getApplicationContext());
        }).start();

        userInput = findViewById(R.id.userInput);
        recyclerView = findViewById(R.id.conversation);

        responseMessageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(responseMessageList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(messageAdapter);

        chatBot.addMonthsToMap();

        userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    // start listening
                    aiService.startListening();
                    // sent from the user
                    ResponseMessage userMessage = new ResponseMessage(userInput.getText().toString(), true);
                    responseMessageList.add(userMessage);

                    String botResponse = chatBot.generateMessageForUserSQL(userMessage.getTextMessage(), listOfHeartRateModels);
                    // sent from the chat bot
                    ResponseMessage botMessage = new ResponseMessage(botResponse, false);
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
     *
     * @ return true or false based on visibility
     */
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

    /**
     * Dialog flow AIResponse message
     *
     * */
    @Override
    public void onResult(AIResponse result) {
        Result r = result.getResult();
        Log.i(TAG, "Reached");
        Log.i(TAG, r.getResolvedQuery());
    }

    @Override
    public void onError(AIError error) {
        Log.d(TAG, error.getMessage());
    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }
}
