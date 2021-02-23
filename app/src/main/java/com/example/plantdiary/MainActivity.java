package com.example.plantdiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements ListFragment.OnShowAddNewFragmentListener, AddNewFragment.OnCancelAddingListener, AddNewFragment.OnAddToListFragment {

    private AddNewFragment addNewFragment;
    private ListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle(R.string.app_name);

        listFragment = new ListFragment();
        addNewFragment = new AddNewFragment();
//        if (addNewFragment.isAdded())
//            return;

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, ListFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void showAddNewFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        if(addNewFragment.isAdded())
//            return;
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

    @Override
    public void addToListFragment(Item item) {
        if (!isTwoPaneView()) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, listFragment);
            transaction.remove(addNewFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            getFragmentManager().executePendingTransactions();
        }
        listFragment.addItem(item);
    }

    private boolean isTwoPaneView() {
        return findViewById(R.id.frame) == null;
    }
}