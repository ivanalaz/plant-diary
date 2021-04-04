package com.example.plantdiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantdiary.db.entity.Plant;

import java.util.Calendar;

public class PlantsAdapter extends ListAdapter<Plant, PlantsAdapter.PlantsViewHolder> {

    protected PlantsAdapter(@NonNull DiffUtil.ItemCallback<Plant> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PlantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        PlantsViewHolder viewHolder = new PlantsViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlantsAdapter.PlantsViewHolder holder, int position) {
        Plant current = getItem(position);
        int currentDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        //int lastWatered = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1; // yesterday; for testing purposes
        int waterInterval = current.getWaterInterval();
        // holder.imageView.setImageBitmap();
        holder.nameTextView.setText(current.getName());
        //if (current.getLastWatered() == null)
            holder.daysLeftTextView.setText(current.getWaterInterval() + " days remaining");
      /*  else {
            int lastWatered = plantList.get(position).getLastWatered().get(Calendar.DAY_OF_MONTH);
            holder.daysLeftTextView.setText((lastWatered + waterInterval - currentDate) + " days remaining");
        }*/
    }

    public static class PlantsViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView nameTextView;
        public TextView daysLeftTextView;

        public PlantsViewHolder(@NonNull View itemView/*, final LiveData<List<Plant>> plantList*/) {
            super(itemView);
            imageView = itemView.findViewById(R.id.smallImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            daysLeftTextView = itemView.findViewById(R.id.daysLeftTextView);
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
