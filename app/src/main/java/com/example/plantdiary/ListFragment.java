package com.example.plantdiary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantdiary.db.PlantViewModel;

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    PlantsAdapter plantsAdapter = new PlantsAdapter(new PlantsAdapter.PlantDiff());

    private PlantViewModel plantViewModel;

    public ListFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());

        plantsAdapter = new PlantsAdapter(new PlantsAdapter.PlantDiff());
        recyclerView.setAdapter(plantsAdapter);
        recyclerView.setLayoutManager(layoutManager);

        plantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);
        plantViewModel.getAllPlants().observe(getViewLifecycleOwner(), plants -> {
            // update the cached copy of the words in the adapter
            plantsAdapter.submitList(plants);
        });

        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
