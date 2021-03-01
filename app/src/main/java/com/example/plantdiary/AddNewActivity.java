package com.example.plantdiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddNewActivity extends AppCompatActivity {

    private Button addButton;
    private Button cancelButton;
    private EditText nameEditText;
    private EditText descEditText;
    private Spinner waterSpinner;
    private Spinner fertSpinner;

    private AddNewFragment.OnAddToListFragment callback;

    public interface OnAddToListFragment {
        void addToListFragment(Item item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addButton = findViewById(R.id.addButton);
        cancelButton = findViewById(R.id.cancelButton);
        nameEditText = findViewById(R.id.nameEditText);
        descEditText = findViewById(R.id.descEditText);
        waterSpinner = findViewById(R.id.waterSpinner);
        fertSpinner = findViewById(R.id.fertSpinner);

        ListFragment listFragment = new ListFragment();
        addButton.setOnClickListener(v -> {
            Intent data = new Intent();
            data.putExtra("name", nameEditText.getEditableText().toString());
            //data.putExtra("description", descEditText.getEditableText().toString());
            data.putExtra("waterInterval", Integer.parseInt(waterSpinner.getSelectedItem().toString()));
            setResult(Activity.RESULT_OK, data);
        /*    Bundle b = new Bundle();
            b.putString("name", nameEditText.getEditableText().toString());
            b.putInt("waterInterval", Integer.parseInt(waterSpinner.getSelectedItem().toString()));
            listFragment.setArguments(b);*/
            // fragmentTransaction.add(R.id.frameLayout, myFragment).commit();
            finish();
        });

        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> finish());

    }

    // IMPLEMENTACIJA addToListFragment ???



    public String getMyData(){
        return "getMyData method in AddNewActivity";
    }

}