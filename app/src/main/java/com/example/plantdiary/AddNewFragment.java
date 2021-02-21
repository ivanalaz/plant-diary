package com.example.plantdiary;

import android.content.Context;
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

public class AddNewFragment extends Fragment {

    private Button addButton;
    private Button cancelButton;
    private EditText nameEditText;
    private EditText descEditText;
    private Spinner waterSpinner;
    private Spinner fertSpinner;

    private OnCancelAddingListener callback;

    public interface OnCancelAddingListener {
        void cancelAdding();
    }

    public interface onAddToListFragment {
        void addToListFragment(Item item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_fragment, container, false);
        addButton = view.findViewById(R.id.addButton);
        cancelButton = view.findViewById(R.id.cancelButton);
        nameEditText = view.findViewById(R.id.nameTextView);
        descEditText = view.findViewById(R.id.descEditText);
        waterSpinner = view.findViewById(R.id.waterSpinner);
        fertSpinner = view.findViewById(R.id.fertSpinner);

        cancelButton.setOnClickListener(v -> callback.cancelAdding());
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callback = (OnCancelAddingListener) context;
    }
}
