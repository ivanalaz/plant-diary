package com.example.plantdiary;

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

        /*
        * buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String text = editTextItem.getEditableText().toString() + ", " + radioButtonDone.isChecked() + ", " + textViewDate.getText();
//                Toast.makeText(AddNewActivity.this, text, Toast.LENGTH_LONG ).show();
                Intent data = new Intent();
                data.putExtra("item", editTextItem.getEditableText().toString());
                data.putExtra("status",radioButtonDone.isChecked());
                data.putExtra("date", textViewDate.getText());
                data.putExtra("time", textViewTime.getText());
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });*/

        ListFragment listFragment = new ListFragment();
        addButton.setOnClickListener(v -> {
        /*    Intent data = new Intent();
            data.putExtra("name", nameEditText.getEditableText().toString());
            //data.putExtra("description", descEditText.getEditableText().toString());
            data.putExtra("waterInterval", Integer.parseInt(waterSpinner.getSelectedItem().toString()));
            setResult(RESULT_OK, data); */
            Bundle b = new Bundle();
            b.putString("name", nameEditText.getEditableText().toString());
            b.putInt("waterInterval", Integer.parseInt(waterSpinner.getSelectedItem().toString()));
            listFragment.setArguments(b);
            // fragmentTransaction.add(R.id.frameLayout, myFragment).commit();
            finish();
        });

        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> finish());
/*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
}