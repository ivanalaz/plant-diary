package com.example.plantdiary;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class AddNewFragment extends Fragment {

    private Button addButton;
    private Button cancelButton;
    private EditText nameEditText;
    private EditText descEditText;
    private Spinner waterSpinner;
    private Spinner fertSpinner;

    private OnCancelAddingListener callback;
    private OnAddToListFragment callbackAdd;

    //public AddNewFragment() {}

    public interface OnCancelAddingListener {
        void cancelAdding();
    }

    public interface OnAddToListFragment {
        void addToListFragment(Item item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_fragment, container, false);
        addButton = view.findViewById(R.id.addButton);
        cancelButton = view.findViewById(R.id.cancelButton);
        nameEditText = view.findViewById(R.id.nameEditText);
        descEditText = view.findViewById(R.id.descEditText);
        waterSpinner = view.findViewById(R.id.waterSpinner);
        fertSpinner = view.findViewById(R.id.fertSpinner);

        addButton.setOnClickListener(v -> {
            Item item = new Item();
            item.setName(nameEditText.getText().toString());
           // item.setLastWatered(Calendar.getInstance());
            item.setWaterInterval(Integer.parseInt(waterSpinner.getSelectedItem().toString()));

            nameEditText.getText().clear();
            waterSpinner.setSelection(0);

            callbackAdd.addToListFragment(item);
        });

        cancelButton.setOnClickListener(v -> callback.cancelAdding());

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        //if (context instanceof OnCancelAddingListener)
            callback = (OnCancelAddingListener) context;
        //else
            callbackAdd = (OnAddToListFragment) context;
    }
}
