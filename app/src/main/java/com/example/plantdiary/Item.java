package com.example.plantdiary;

import java.util.Date;

public class Item {

    private String name;
    private String description;
    private int waterInterval;
    private int fertInterval;
    private Date lastWatered;
    private Date lastFertilised;

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

    public Date getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(Date lastWatered) {
        this.lastWatered = lastWatered;
    }

    public Date getLastFertilised() {
        return lastFertilised;
    }

    public void setLastFertilised(Date lastFertilised) {
        this.lastFertilised = lastFertilised;
    }
}
