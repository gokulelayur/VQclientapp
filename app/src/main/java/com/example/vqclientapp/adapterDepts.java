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

public class adapterDepts extends RecyclerView.Adapter<adapterDepts.Viewholder> {

    ArrayList<department> deptlist;
    Context context;
    private deptListListner deptListListner;

    public adapterDepts(ArrayList<department> deptlist, Context context, deptListListner deptListListner) {
        this.deptlist = deptlist;
        this.context = context;
        this.deptListListner = deptListListner;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);

        return new Viewholder(view, deptListListner);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        department dep = deptlist.get(position);
        holder.deptname.setText(dep.getOgDeptName().toUpperCase());
        holder.currentToken.setText(String.valueOf(dep.getCurrenttoken()));
    }

    @Override
    public int getItemCount() {
        return deptlist.size();
    }

    class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView deptname,currentToken;
        deptListListner listnerDeptList;

        public Viewholder(@NonNull View itemView, deptListListner listnerDeptList) {
            super(itemView);
            deptname = itemView.findViewById(R.id.deptName);
            currentToken=itemView.findViewById(R.id.deptCurrentToken);
            this.listnerDeptList = listnerDeptList;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listnerDeptList.onDeptListClick(getAdapterPosition());
        }
    }

    public interface deptListListner {
        void onDeptListClick(int position);
    }
}
