package com.example.plantdiary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements ListFragment.OnShowAddNewFragmentListener, AddNewFragment.OnCancelAddingListener {

    private AddNewFragment addNewFragment;
    private ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = new ListFragment();
        addNewFragment = new AddNewFragment();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, ListFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void showAddNewFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, addNewFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        getFragmentManager().executePendingTransactions();
    }

    @Override
    public void cancelAdding() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, listFragment);
        transaction.remove(addNewFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        getFragmentManager().executePendingTransactions();
    }
}