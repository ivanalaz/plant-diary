package com.example.plantdiary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListFragment.OnShowAddNewFragmentListener, AddNewFragment.OnCancelAddingListener, AddNewFragment.OnAddToListFragment {

    private AddNewFragment addNewFragment;
    private ListFragment listFragment;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FloatingActionButton fab;
    private final int LAUNCH_SECOND_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        addNewFragment = new AddNewFragment();
        listFragment = new ListFragment();

/*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, ListFragment.newInstance(), "listFragment")
                    .commitNow();
        }
*/
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
           // showAddNewFragment();
            Intent intent1 = new Intent(MainActivity.this, AddNewActivity.class);
            startActivityForResult(intent1, LAUNCH_SECOND_ACTIVITY);
           // startActivity(new Intent(YourCurrentActivity.this,NewActivity.class));
           // fab.setVisibility(View.INVISIBLE);
        });

    }

    /* ***** RADI **** */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                Log.i("PlantDiary", "onActivityResult RESULT_OK");
                Item item = new Item();
                item.setName(data.getStringExtra("name"));
                item.setWaterInterval(data.getIntExtra("waterInterval", 1));
              //  listFragment.addItem(item);
                addToListFragment(item);
                Log.i("PlantDiary", "item name: " + item.getName());
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                Log.i("PlantDiary", "onActivityResult RESULT_CANCELED");
            }
        }
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fab.setVisibility(View.VISIBLE);
    }


    @Override
    public void showAddNewFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        tabLayout.setVisibility(View.GONE);
        if(addNewFragment.isAdded())
            return;
        transaction.replace(R.id.frame, addNewFragment);
        transaction.addToBackStack(null);
        transaction.hide(listFragment).commit();
        getFragmentManager().executePendingTransactions();
    }

    @Override
    public void cancelAdding() {
        fab.setVisibility(View.VISIBLE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, listFragment);
        transaction.remove(addNewFragment);
        transaction.addToBackStack(null);
        //transaction.hide(addNewFragment).commit();
        transaction.commit();
        getFragmentManager().executePendingTransactions();
    }

    @Override
    public void addToListFragment(Item item) {
        fab.setVisibility(View.VISIBLE);
        if (!isTwoPaneView()) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, listFragment);
            transaction.remove(addNewFragment);
            transaction.addToBackStack(null);
            // transaction.hide(addNewFragment).commit();
            transaction.commit();
            getFragmentManager().executePendingTransactions();
        }
        listFragment.addItem(item);
    }

    private boolean isTwoPaneView() {
            return findViewById(R.id.frame) == null;
        }

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
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ListFragment(), "Today");
        adapter.addFragment(new TodayFragment(), "All plants");
        viewPager.setAdapter(adapter);
    }
}
/* public class CustomPagerAdapter extends FragmentStatePagerAdapter {
    public int mNumOfTabs;

    public CustomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ListFragment();
            case 1:
                return new AddNewFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}*/
