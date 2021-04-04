package com.example.plantdiary.db;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.plantdiary.db.entity.Plant;
import com.example.plantdiary.db.repository.PlantRepository;

import java.util.List;

public class PlantViewModel extends AndroidViewModel {
    private PlantRepository repository;
    private final LiveData<List<Plant>> allPlants;

    public PlantViewModel(Application application) {
        super(application);
        repository = new PlantRepository(application);
        allPlants = repository.getAllPlants();
    }

    // returns a cached list of words
    public LiveData<List<Plant>> getAllPlants() { return allPlants; }

    // Created a wrapper insert() method that calls the Repository's insert() method.
    // In this way, the implementation of insert() is encapsulated from the UI
    public void insert(Plant plant) { repository.insert(plant); }
}
