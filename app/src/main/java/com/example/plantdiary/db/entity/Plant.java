package com.example.plantdiary.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "plant")
public class Plant {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private String description;
    @ColumnInfo(name = "water_interval")
    private int waterInterval;
    @ColumnInfo(name = "fert_interval")
    private int fertInterval;
   /* @ColumnInfo(name = "last_watered")
    private Calendar lastWatered;
    @ColumnInfo(name = "last_fertilized")
    private Calendar lastFertilised;*/

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

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
/*
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
    }*/
}
