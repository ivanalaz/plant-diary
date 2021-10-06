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

    @Query("SELECT * FROM plant ORDER BY last_watered ASC")
    LiveData<List<Plant>> getAll();

    @Insert
    void insert(Plant Plant);

    @Update
    void update(Plant Plant);

    @Delete
    void delete(Plant Plant);

    @Query("DELETE FROM plant")
    void deleteAll();

    @Query("SELECT * FROM plant WHERE id = :plantId")
    Plant getPlant(int plantId);

    //@Query("SELECT * FROM plant WHERE ((SELECT (JULIANDAY('now') - JULIANDAY(last_watered))) + water_interval) < 0")
    //@Query("SELECT * FROM plant WHERE (date(CURRENT_TIMESTAMP / 1000,'unixepoch') - date(last_watered / 1000,'unixepoch')) < 1")
    @Query("SELECT * FROM plant WHERE water_interval <= :interval")
    LiveData<List<Plant>> getRecents(int interval);

    @Query("SELECT * FROM plant WHERE water_interval <= :interval")
    List<Plant> getRecent(int interval);
}
