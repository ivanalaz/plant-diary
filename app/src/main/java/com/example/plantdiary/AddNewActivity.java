package com.example.plantdiary;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddNewActivity extends AppCompatActivity {

    private Button addButton;
    private Button cancelButton;
    private EditText nameEditText;
    private EditText descEditText;
    private Spinner waterSpinner;
    private Spinner fertSpinner;

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

        addButton.setOnClickListener(v -> {
            Intent data = new Intent();
            if (TextUtils.isEmpty(nameEditText.getText())) {
                setResult(RESULT_CANCELED, data);
            } else {
                data.putExtra("name", nameEditText.getEditableText().toString());
                data.putExtra("description", descEditText.getEditableText().toString());
                data.putExtra("waterInterval", Integer.parseInt(waterSpinner.getSelectedItem().toString()));
                setResult(RESULT_OK, data);
            }
            finish();
        });

        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> finish());
    }
}