package com.example.plantdiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantdiary.db.entity.Plant;
import com.example.plantdiary.db.viewmodels.PlantViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TodayFragment extends Fragment {

    private RecyclerView recyclerView;
    private PlantsAdapter plantsAdapter;
    private PlantViewModel plantViewModel;

    public TodayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewToday);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        plantsAdapter = new PlantsAdapter(new PlantsAdapter.PlantDiff(), getContext());
        recyclerView.setAdapter(plantsAdapter);

        plantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);
        plantViewModel.getRecents(1).observe(getViewLifecycleOwner(), plants -> {
            // update the cached copy of the words in the adapter
            List<Plant> todayList = new ArrayList<>();
            for (Plant plant: plants) {
                if ((dateDifference(new Date(), plant.getLastWatered()) + plant.getWaterInterval()) < 1) {
                    todayList.add(plant);
                }
            }
            plantsAdapter.submitList(plants);
        });
        return view;
    }

    private long dateDifference(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}