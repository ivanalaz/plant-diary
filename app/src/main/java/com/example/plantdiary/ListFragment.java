package com.example.plantdiary;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    PlantsAdapter plantsAdapter;
    TabLayout tabs;
    //ViewPager viewPager;
    private OnShowAddNewFragmentListener callback;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    interface OnShowAddNewFragmentListener {
        void showAddNewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);

        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_list, container, false);

        tabs = view.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Today"));
        tabs.addTab(tabs.newTab().setText("All plants"));
/*
        viewPager = view.findViewById(R.id.viewpager);
        tabs.setupWithViewPager(viewPager);
*/
        recyclerView = view.findViewById(R.id.recyclerView);
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        /* PRIMER *
        List<Item> itemList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {

            Item item = new Item();
            item.setName("plant " + i);
            item.setWaterInterval(5);
            itemList.add(item);
        }
        plantsAdapter = new PlantsAdapter(itemList);*/
        recyclerView.setAdapter(plantsAdapter);

        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> callback.showAddNewFragment());

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        plantsAdapter = new PlantsAdapter(new ArrayList<Item>());
        setRetainInstance(true);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callback = (OnShowAddNewFragmentListener) context;
    }

    public void addItem(Item item) {
        plantsAdapter.add(item);
    }

}
