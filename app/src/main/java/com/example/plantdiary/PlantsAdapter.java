package com.example.plantdiary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlantsAdapter extends RecyclerView.Adapter<PlantsAdapter.PlantsViewHolder> {
    List<Item> itemList;

    public PlantsAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public PlantsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        PlantsViewHolder viewHolder = new PlantsViewHolder(v, itemList);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlantsViewHolder holder, int position) {
        // holder.imageView.setImageBitmap();
        holder.nameTextView.setText(itemList.get(position).getName());
        holder.daysLeftTextView.setText(/* num + */" days remaining");
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class PlantsViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView nameTextView;
        public TextView daysLeftTextView;

        public PlantsViewHolder(@NonNull View itemView, final List<Item> itemList) {
            super(itemView);
            imageView = itemView.findViewById(R.id.smallImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            daysLeftTextView = itemView.findViewById(R.id.daysLeftTextView);
        }
    }

    public void add(Item item) {
        itemList.add(item);
        notifyDataSetChanged();
    }
}
