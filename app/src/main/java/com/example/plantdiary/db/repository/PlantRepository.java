package com.example.plantdiary.db.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.plantdiary.db.PlantDatabase;
import com.example.plantdiary.db.dao.PlantDao;
import com.example.plantdiary.db.entity.Plant;

import java.util.List;

public class PlantRepository {

    private final PlantDao plantDao;
    private final LiveData<List<Plant>> allPlants;

    // You only need access to the DAO
    // There's no need to expose the entire database to the repository.
    public PlantRepository(Application application) {
        PlantDatabase db = PlantDatabase.getDatabase(application);
        plantDao = db.plantDao();
        allPlants = plantDao.getAll();
    }

    public LiveData<List<Plant>> getAllPlants() {
        return allPlants;
    }

    // to perform the insert on a background thread
    public void insert(Plant plant) {
        PlantDatabase.databaseWriteExecutor.execute(() ->
                plantDao.insert(plant));
    }

    public void update(Plant plant) {
        PlantDatabase.databaseWriteExecutor.execute(() ->
                plantDao.update(plant));
    }

    public void delete(Plant plant) {
        PlantDatabase.databaseWriteExecutor.execute(() ->
                plantDao.delete(plant));

    }

    public Plant getPlant(int id) {
        return plantDao.getPlant(id);
    }

    public LiveData<List<Plant>> getRecents(int interval) { return plantDao.getRecents(interval); }

    public List<Plant> getRecent(int interval) { return plantDao.getRecent(interval); }

}
