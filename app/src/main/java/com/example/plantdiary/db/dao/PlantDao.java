package com.example.plantdiary.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.plantdiary.db.entity.Plant;

import java.util.List;

@Dao
public interface PlantDao {

    @Query("SELECT * FROM plant")
    LiveData<List<Plant>> getAll();

    @Insert
    void insert(Plant Plant);

    @Update
    void update(Plant Plant);

    @Delete
    void delete(Plant Plant);

    @Query("DELETE FROM plant")
    void deleteAll();
}
