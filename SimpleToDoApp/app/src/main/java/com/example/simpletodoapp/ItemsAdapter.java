package com.example.simpletodoapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

//responsible for displaying data from one model in a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnlongClickListener {
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnlongClickListener longClickListener;

    public ItemsAdapter(List<String> items, OnlongClickListener longClickListener) { //stores data about the model
        this.items = items;
        this.longClickListener = longClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use layout inflater to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        //wrap it inside a View Holder and return it
        return new ViewHolder(todoView);
    }

    //binds data to particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //grab the item at the position
        String item = items.get(position);
        //Bind the item into the view holder
        holder.bind(item);
    }

    //tells the RV how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //container to provide access to views that represent rows
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItems;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItems = itemView.findViewById(android.R.id.text1);
        }

        //update the view inside of the view holder with this data
        public void bind(String item) {
            tvItems.setText(item);
            tvItems.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //notify the listener which position was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }

}
