package com.example.plantdiary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.plantdiary.db.entity.Plant;
import com.example.plantdiary.db.viewmodels.PlantViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PlantsAdapter extends ListAdapter<Plant, PlantsAdapter.PlantsViewHolder> {

    private Context context;
    private PlantViewModel plantViewModel;

    protected PlantsAdapter(@NonNull DiffUtil.ItemCallback<Plant> diffCallback, Context context) {
        super(diffCallback);
        this.context = context;
        plantViewModel = new ViewModelProvider((MainActivity) context).get(PlantViewModel.class);

    }

    RecyclerView recyclerView;
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public PlantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        PlantsViewHolder viewHolder = new PlantsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlantsAdapter.PlantsViewHolder holder, int position) {
        Plant current = getItem(position);
        Glide.with(holder.imageView)
                .load(current.getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);

        holder.nameTextView.setText(current.getName());
        if (current.getLastWatered() == null)
            holder.daysLeftTextView.setText(current.getWaterInterval() + " days remaining");
        else {
            Date lastWateredDate = current.getLastWatered();
            Date today = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(lastWateredDate);
            long days = dateDifference(today, lastWateredDate) + current.getWaterInterval();
            String text = " days remaining";
            if (days == 1)
                text = " day remaining";
            holder.daysLeftTextView.setText(days + text);
        }
        String plantName = current.getName();
        holder.popup.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(context, v);
            popup.inflate(R.menu.item_options);
            popup.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.edit:
                        Intent intent1 = new Intent(context, EditActivity.class);
                        intent1.putExtra("plantId", current.getId());
                        ((Activity) context).startActivity(intent1);
                        return true;
                    case R.id.delete:
                        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    //Yes button clicked
                                    plantViewModel.delete(current);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete " + plantName + "?").setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();
                        return true;
                    default:
                        return false;
                }
            });
            popup.show();
        });
        holder.view.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlantDetailActivity.class);
            intent.putExtra("plantId", current.getId());
            context.startActivity(intent);
        });

    }

    private long dateDifference(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static class PlantsViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView nameTextView;
        public TextView daysLeftTextView;
        public ImageButton popup;
        public View view;

        public PlantsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.smallImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            daysLeftTextView = itemView.findViewById(R.id.daysLeftTextView);
            popup = itemView.findViewById(R.id.popup);
            view = itemView;
        }
    }

    static class PlantDiff extends DiffUtil.ItemCallback<Plant> {

        @Override
        public boolean areItemsTheSame(@NonNull Plant oldItem, @NonNull Plant newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Plant oldItem, @NonNull Plant newItem) {
            return oldItem.getId().equals(newItem.getId());
        }
    }
}
