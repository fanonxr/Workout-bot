package com.fanonx.chatbot_demo.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.fanonx.chatbot_demo.models.ActivfitModel;

import java.util.List;

@Dao
public interface ActivfitModelDao {
    /** method to insert the data into the database */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ActivfitModel> model);
    /** method to return all the data from the database as objects*/
    @Query("SELECT * from activfit")
    List<ActivfitModel> getAllModels();
}
