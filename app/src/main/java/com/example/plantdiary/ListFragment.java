package com.example.plantdiary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    PlantsAdapter plantsAdapter = new PlantsAdapter(new ArrayList<>());
    TabLayout tabs;
    ViewPager viewPager;
    private OnShowAddNewFragmentListener callback;

    private final int LAUNCH_SECOND_ACTIVITY = 1;

    public ListFragment() {

    }
    public static ListFragment newInstance() {
        return new ListFragment();
    }

    interface OnShowAddNewFragmentListener {
        void showAddNewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         //super.onCreateView(inflater, container, savedInstanceState);

        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            String name = getArguments().getString("name");
//            int waterInterval = getArguments().getInt("waterInterval");

        List<Item> itemList = new ArrayList<>();
/* PRIMER *
        for (int i = 0; i < 1; i++) {
            Item item = new Item();
           // item.setName(getActivity().getIntent().getExtras().getString("name"));
           // item.setWaterInterval(getActivity().getIntent().getExtras().getInt("waterInterval"));
            itemList.add(item);
            Log.i("inside ListFragment", getActivity().getIntent().getExtras().getString("name") + "");
        }
      /*  Intent intent = getActivity().getIntent();
        startActivityForResult(intent, LAUNCH_SECOND_ACTIVITY);*/

//        AddNewActivity activity = (AddNewActivity) getActivity();
//        String myDataFromActivity = activity.getMyData();


        plantsAdapter = new PlantsAdapter(itemList);
      //  plantsAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(plantsAdapter);

      //  fab = view.findViewById(R.id.fab);
//        fab.setOnClickListener(v -> callback.showAddNewFragment());

        return view;
    }

/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                Log.i("PlantDiary", "onActivityResult RESULT_OK");
                Item item = new Item();
                item.setName(data.getStringExtra("name"));
                item.setWaterInterval(data.getIntExtra("waterInterval", 1));
                plantsAdapter.add(item);
                Log.i("PlantDiary", "item name: " + item.getName());
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                Log.i("PlantDiary", "onActivityResult RESULT_CANCELED");
            }
        }
    }
*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // plantsAdapter = new PlantsAdapter(new ArrayList<>());
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
/*
    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> titleList = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            titleList.add(title);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new ListFragment(), "Today");
        adapter.addFragment(new AddNewFragment(), "All plants");
        viewPager.setAdapter(adapter);
    }
*/
}
