package com.example.plantdiary;

import java.util.Calendar;
import java.util.Date;

public class Item {

    private String name;
    private String description;
    private int waterInterval;
    private int fertInterval;
    private Calendar lastWatered;
    private Calendar lastFertilised;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWaterInterval() {
        return waterInterval;
    }

    public void setWaterInterval(int waterInterval) {
        this.waterInterval = waterInterval;
    }

    public int getFertInterval() {
        return fertInterval;
    }

    public void setFertInterval(int fertInterval) {
        this.fertInterval = fertInterval;
    }

    public Calendar getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(Calendar lastWatered) {
        this.lastWatered = lastWatered;
    }

    public Calendar getLastFertilised() {
        return lastFertilised;
    }

    public void setLastFertilised(Calendar lastFertilised) {
        this.lastFertilised = lastFertilised;
    }
}
