package com.fanonx.chatbot_demo.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.fanonx.chatbot_demo.models.ActivfitModel;

import java.util.List;

@Dao
public interface ActivfitModelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ActivfitModel> model);
    @Query("SELECT * from activfit")
    List<ActivfitModel> getAllModels();
}
