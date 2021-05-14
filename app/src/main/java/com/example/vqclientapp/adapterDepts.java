package com.example.vqclientapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class adapterDepts extends RecyclerView.Adapter<adapterDepts.Viewholder>{

    ArrayList<department> deptlist;
    Context context;

    public adapterDepts(ArrayList<department> deptlist, Context context) {
        this.deptlist = deptlist;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.row_item,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        department dep=deptlist.get(position);
        holder.deptname.setText(dep.getName());
    }

    @Override
    public int getItemCount() {
        return deptlist.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView deptname;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
    //        img=itemView.findViewById(R.id.deptIcon);
            deptname=itemView.findViewById(R.id.deptName);

        }
    }
}
