package com.example.vqclientapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class adapterQueueRecycler extends RecyclerView.Adapter<adapterQueueRecycler.Viewholder>{
    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_queue_recycler,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class Viewholder extends RecyclerView.ViewHolder{

        TextView tokenName;

        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            tokenName=itemView.findViewById(R.id.queuetokenName);
        }
    }
}
