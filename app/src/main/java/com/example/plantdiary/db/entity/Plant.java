package com.example.plantdiary.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

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
    @ColumnInfo(name = "last_watered")
    private Date lastWatered;
    @ColumnInfo(name = "last_fertilized")
    private Date lastFertilised;
   // @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private String image;

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

    public Date getLastWatered() { return lastWatered; }

    public void setLastWatered(Date lastWatered) { this.lastWatered = lastWatered; }

    public Date getLastFertilised() { return lastFertilised; }

    public void setLastFertilised(Date lastFertilised) { this.lastFertilised = lastFertilised; }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }
}
