package com.example.plantdiary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle(R.string.app_name);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
       // adapter = new CustomPagerAdapter(getSupportFragmentManager());
        //viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        addNewFragment = new AddNewFragment();
        listFragment = new ListFragment();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            showAddNewFragment();
          /*  Intent intent1 = new Intent(MainActivity.this, AddNewFragment.class);
            startActivity(intent1); */
            fab.setVisibility(View.INVISIBLE);
        });

/* ovo je bilo



 */
//        if (addNewFragment.isAdded())
//            return;
/* ovo je bilo
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, ListFragment.newInstance())
                    .commitNow();
        }*/
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fab.setVisibility(View.VISIBLE);
    }


        @Override
        public void showAddNewFragment() {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
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
