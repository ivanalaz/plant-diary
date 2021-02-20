package com.example.plantdiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements ListFragment.OnShowAddNewFragmentListener {

    private ListFragment listFragment;
   // private Button addNewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = new ListFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.add(R.id.frame, listFragment);
        //transaction.add(R.id.addNewButton, listFragment);

        transaction.commit();
    }

    @Override
    public void showAddNewFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
       // transaction.replace()
    }
}