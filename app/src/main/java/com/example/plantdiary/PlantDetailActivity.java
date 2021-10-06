package com.example.plantdiary;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.plantdiary.db.entity.Plant;
import com.example.plantdiary.db.viewmodels.PlantViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PlantDetailActivity extends AppCompatActivity {

    private ImageButton calendar;
    private ImageButton calendarFert;
    private static TextView lastWatered;
    private static TextView lastFertilized;
    private TextView waterIntervalText;
    private TextView fertIntervalText;
    private TextView description;

    private static PlantViewModel plantViewModel;
    private static int datePicker; 
    private static int plantId;

    static Plant plant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail);

        Intent intent = this.getIntent();
        plantId = (int) intent.getSerializableExtra("plantId");

        plantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);

        plant = plantViewModel.getPlant(plantId);
        String plantName = plant.getName();
        waterIntervalText = findViewById(R.id.water_interval);
        waterIntervalText.setText("Water interval: " + plant.getWaterInterval());
        fertIntervalText = findViewById(R.id.fert_interval);
        fertIntervalText.setText("Fertilization interval: " + plant.getFertInterval());

        description = findViewById(R.id.description);
        if (plant.getDescription() != null)
            description.setText(plant.getDescription());

        Toolbar toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(plantName);

        FloatingActionButton fab = findViewById(R.id.edit_fab);
        fab.setOnClickListener(v -> {
            Intent intent1 = new Intent(PlantDetailActivity.this, EditActivity.class);
            intent1.putExtra("plantId", plant.getId());
            startActivity(intent1);
        });

        loadBackdrop();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        lastWatered = findViewById(R.id.last_watered);
        if (plant.getLastWatered() != null)
            lastWatered.setText(sdf.format(plant.getLastWatered()));

        calendar = findViewById(R.id.calendar);
        calendar.setOnClickListener(view -> {
            datePicker = view.getId();
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");
        });

        lastFertilized = findViewById(R.id.last_fertilized);
        if (plant.getLastFertilised() != null)
            lastFertilized.setText(sdf.format(plant.getLastFertilised()));

        calendarFert = findViewById(R.id.calendarFert);
        calendarFert.setOnClickListener(view -> {
            datePicker = view.getId();
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");
        });
    }

    private void loadBackdrop() {
        ImageView imageView = findViewById(R.id.backdrop);
        Glide.with(imageView)
                .load(plant.getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(imageView);
        Log.i("IMAGE URI FROM PLANT", plant.getImage());
    }

    // back in the action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day, 0, 0);
            switch (datePicker) {
                case R.id.calendar:
                    lastWatered.setText(day + "." + (month+1) + "." + year + ".");
                    plant.setLastWatered(calendar.getTime());
                    plantViewModel.update(plant);
                    break;
                case R.id.calendarFert:
                    lastFertilized.setText(day + "." + (month+1) + "." + year + ".");
                    plant.setLastFertilised(calendar.getTime());
                    plantViewModel.update(plant);
                    break;
                default:
                    break;
            }

        }

    }
}

