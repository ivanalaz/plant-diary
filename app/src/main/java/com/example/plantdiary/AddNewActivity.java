package com.example.plantdiary;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.plantdiary.db.entity.Plant;
import com.example.plantdiary.db.viewmodels.PlantViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddNewActivity extends AppCompatActivity {

    private Button addButton;
    private Button cancelButton;
    private EditText nameEditText;
    private EditText descEditText;
    private Spinner waterSpinner;
    private Spinner fertSpinner;
    private ImageButton imageButton;

    private static final int EMPTY_TEXT = 13;

    private static final int SELECT_PHOTO = 17;
    private static final String TAG = "ImagePicker";
    private static final String TEMP_IMAGE_NAME = "tempImage";
    Uri selectedImageUri;

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
        imageButton = findViewById(R.id.imageButton);

        PlantViewModel plantViewModel = new ViewModelProvider(this).get(PlantViewModel.class);

        imageButton.setOnClickListener(v -> {
            Intent chooseImageIntent = getPickImageIntent(this);
            startActivityForResult(chooseImageIntent, SELECT_PHOTO);
        });

        addButton.setOnClickListener(v -> {
            if (TextUtils.isEmpty(nameEditText.getText())) {
                setResult(EMPTY_TEXT);
            } else {
                Plant plant = new Plant();
                plant.setName(nameEditText.getEditableText().toString());
                plant.setDescription(descEditText.getEditableText().toString());
                plant.setWaterInterval(Integer.parseInt(waterSpinner.getSelectedItem().toString()));
                plant.setFertInterval(Integer.parseInt(fertSpinner.getSelectedItem().toString()));
                if (selectedImageUri != null)
                    plant.setImage(selectedImageUri.toString());
                plantViewModel.insert(plant);
            }
            finish();
        });

        cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> finish());
    }

    public static Intent getPickImageIntent(Context context) {
        Intent chooserIntent = null;

        List<Intent> intentList = new ArrayList<>();

        Intent pickIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePhotoIntent.putExtra("return-data", true);
        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTempFile(context)));
        intentList = addIntentsToList(context, intentList, pickIntent);
        intentList = addIntentsToList(context, intentList, takePhotoIntent);

        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1),
                    context.getString(R.string.pick_image_intent_text));
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }

        return chooserIntent;
    }

    private static List<Intent> addIntentsToList(Context context, List<Intent> list, Intent intent) {
        List<ResolveInfo> resInfo = context.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resInfo) {
            String packageName = resolveInfo.activityInfo.packageName;
            Intent targetedIntent = new Intent(intent);
            targetedIntent.setPackage(packageName);
            list.add(targetedIntent);
            Log.d(TAG, "Intent: " + intent.getAction() + " package: " + packageName);
        }
        return list;
    }
    private static File getTempFile(Context context) {
        File imageFile = new File(context.getExternalCacheDir(), TEMP_IMAGE_NAME);
        imageFile.getParentFile().mkdirs();
        return imageFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("CALLED", "OnActivity Result");
        if (requestCode == SELECT_PHOTO ) {
            Log.i("requestCode", requestCode + "");
            Log.i("resultCode", resultCode + "");
            if (resultCode == RESULT_OK) {
                selectedImageUri = data.getData();
                imageButton.setImageURI(selectedImageUri);
            }
        }
    }
}