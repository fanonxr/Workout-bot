package com.fanonx.chatbot_demo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.fanonx.chatbot_demo.models.ActivfitModel;

@Database(entities = {ActivfitModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    /**
     * AppDatabase class
     * with @Database annotation
     * */
    private static AppDatabase INSTANCE;

    /**
     * Constructor
     * creating an Instance of the Room using its build method
     * */
    private static AppDatabase buildDatabase(Context appContext) {
        return Room.databaseBuilder(appContext, AppDatabase.class, "app_database.db")
                .build();
    }

    /**
     * Method to retrieve the instance of getting the database.
     * @param appContext the context of the app
     * */
    public static AppDatabase getInstance(Context appContext) {
        if (INSTANCE == null)
            INSTANCE = buildDatabase(appContext);
        return INSTANCE;
    }
    /**
     * abstract Method to call the ActivFitModelDao */
    public abstract ActivfitModelDao activfitModelDao();
}
