package com.fanonx.chatbot_demo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.fanonx.chatbot_demo.models.ActivfitModel;

@Database(entities = {ActivfitModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    private static AppDatabase buildDatabase(Context appContext) {
        return Room.databaseBuilder(appContext, AppDatabase.class, "app_database.db")
                .build();
    }

    public static AppDatabase getInstance(Context appContext) {
        if (INSTANCE == null)
            INSTANCE = buildDatabase(appContext);

        return INSTANCE;
    }

    public abstract ActivfitModelDao activfitModelDao();
}
