package com.fanonx.chatbot_demo.chatbot;

import android.util.Log;

import com.fanonx.chatbot_demo.App;
import com.fanonx.chatbot_demo.models.ActivfitModel;
import com.fanonx.chatbot_demo.models.ActivityModel;
import com.fanonx.chatbot_demo.models.HeartRateModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatBot {

    /**
     * method to extract the date from the user
     */
    private static final String TAG = ChatBot.class.getSimpleName();
    private Map<String, String> monthMap;

    private List<HeartRateModel> listOfHeartRateModels;
    private List<ActivfitModel> activfitModels;
    private List<ActivityModel> activityModels;

    public ChatBot(App app) {
        this.monthMap = new HashMap<String, String>();
        listOfHeartRateModels = app.getHeartRateModels();
        activityModels = app.getActivityModels();
        activfitModels = app.getActivfitModels();
    }

    public void addMonthsToMap() {
        this.monthMap.put("1", "Jan");
        this.monthMap.put("2", "Feb");
        this.monthMap.put("3", "Mar");
        this.monthMap.put("4", "Apr");
        this.monthMap.put("5", "May");
        this.monthMap.put("6", "Jun");
        this.monthMap.put("7", "Jul");
        this.monthMap.put("8", "Aug");
        this.monthMap.put("9", "Sep");
        this.monthMap.put("10", "Oct");
        this.monthMap.put("11", "Nov");
        this.monthMap.put("12", "Dec");
    }

    /**
     * Helper method to parse out the date the user entered.
     * @param userInput what the user typed in the keyboard
     * */
    private String extractDateFromUserQuery(String userInput) {
        // data input needs to be formatted as day/month/year for this regex to work
        // TODO: find a better solution to parse out the date from the user

        // use regex to extract only the date from the string
        int count = 0;
        String dateRegex = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
        String[] allMatches = new String[2];
        Pattern datePattern = Pattern.compile(dateRegex);
        Matcher m = datePattern.matcher(userInput);
        while (m.find()) {
            allMatches[count] = m.group();
            count++;
        }
        // split the date to extract the month
        String[] dateSeparate = allMatches[0].split("/");
        // get the month
        String month = this.monthMap.get(dateSeparate[1]);
        // reformat the date string
        return month + " " + dateSeparate[0] + " " + dateSeparate[2];

        // Jun 12 2017
    }

    /**
     * Helper method to compare dates extracted from the object and database.
     * @param date1 the first date to compare
     * @param date2 the second date to compare
     * */
    private boolean compareDates(String date1, String date2) {
        if (date1.compareTo(date2) == 0) return true;
        return false;
    }

    /**
     * method to generate a response to the user based on the type of query.
     * */
    public String generateMessageForUserSQL(String userInput) {
        // check to see what type of query it is based on the word

        // search for the users heart rate
        if (userInput.contains("heart")) {
            return getHeartRateWithSQL(userInput);
        }

        // search for what type of activity the user did
        if (userInput.contains("activity")) {
            return getActivfitWithSQL(userInput);
        }

        // search for how many steps the user made that day
        if (userInput.contains("steps")) {
            return getStepCountWithSQL(userInput);
        }

        return "sorry, I don't understand your query.";
    }



    /**
     * Method to get the bpm heart rate of a user input on a specific day.
     * @param userInput what the user has typed
     * */
    private String getHeartRateWithSQL(String userInput) {
        Log.i(TAG, "Search heart rate models list");
        // loop over the list of objects
        for (HeartRateModel model: this.listOfHeartRateModels) {
            // get the timestamp date from model
            String timestamp = model.getTimestamp();
            // split into separate parts
            String[] timestampSep = timestamp.split(" ");
            // form the need string back together -> Month Date Year
            String comparedTime = timestampSep[1] + " " + timestampSep[2] + " " + timestampSep[timestampSep.length - 1];
            // extract date from user input
            String extractedDateFromUser = extractDateFromUserQuery(userInput);

            // compare the extracted date from user input with heartRateModel
            if (compareDates(extractedDateFromUser, comparedTime)) {
                // return the heart rate value
                return "Your heart rate for " + extractedDateFromUser + " is " + model.getSensorData().getBpm() + "BPM";
            }
        }

        // return
        return "No heartrate for the day";
    }
    // TODO: search method for activfit and activity

    /** method to get the type of activity the user did */
    private String getStepCountWithSQL(String userInput) {
        Log.i(TAG, "searching activity models list");
        // for over each model
        for (ActivityModel model: this.activityModels) {
            // get the time step from the model
            String timestamp = model.getTimeStamp();
            // split into separate parts
            String[] timestampSep = timestamp.split(" ");
            // form the need string back together -> Month Date Year
            String comparedTime = timestampSep[1] + " " + timestampSep[2] + " " + timestampSep[timestampSep.length - 1];
            // extract date from user input
            String extractedDateFromUser = extractDateFromUserQuery(userInput);

            // compare the extracted date from user input with heartRateModel
            if (compareDates(extractedDateFromUser, comparedTime)) {
                // return the heart rate value
                return "Your total amount of steps for the day are " + model.getSensorData().getStepCounts();
            }
        }
        // no count of steps for the day
        return "I don't have any records for step counts for that day";
    }

    /**
     * Method to check if the user has done an activity for the day
     * */
    private String getActivfitWithSQL(String userInput) {
        Log.i(TAG, "searching activity models list");
        // for over each model
        for (ActivfitModel model: this.activfitModels) {
            // get the time step from the model
            String timestamp = model.getTimeStamp().getStartTime();
            // split into separate parts
            String[] timestampSep = timestamp.split(" ");
            // form the need string back together -> Month Date Year
            String comparedTime = timestampSep[1] + " " + timestampSep[2] + " " + timestampSep[timestampSep.length - 1];
            // extract date from user input
            String extractedDateFromUser = extractDateFromUserQuery(userInput);

            // compare the extracted date from user input with heartRateModel
            if (compareDates(extractedDateFromUser, comparedTime)) {
                // return the heart rate value
                return "Your activity was " + model.getSensorData().getActivity() + " for a duration of " + model.getSensorData().getDuration();
            }
        }
        // no count of steps for the day
        return "I don't have any records for step counts for that day";
    }

    /**
     * Method to send user's input to dialog flow
     * */
    public void sendMessageToDialogFlow(String userInput) {
        
    }

}
