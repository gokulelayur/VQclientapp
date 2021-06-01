package com.example.vqclientapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class adapterQueueRecycler extends RecyclerView.Adapter<adapterQueueRecycler.Viewholder> {
    ArrayList<token> tokenArrayList;

    public adapterQueueRecycler(ArrayList<token> tokenArrayList) {
        this.tokenArrayList = tokenArrayList;
    }

    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_queue_recycler, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholder holder, int position) {
        token thisToken = tokenArrayList.get(position);
        holder.tokenNo.setText(String.valueOf(thisToken.getTokenNo()));
        holder.tokenName.setText(thisToken.getTokenName());
    }

    @Override
    public int getItemCount() {
        return tokenArrayList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {

        TextView tokenName, tokenNo;

        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            tokenName = itemView.findViewById(R.id.queuetokenName);
            tokenNo = itemView.findViewById(R.id.queuetokenNo);
        }
    }
}
