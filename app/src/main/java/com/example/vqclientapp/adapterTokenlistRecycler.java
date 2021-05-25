package com.example.vqclientapp;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class adapterTokenlistRecycler extends RecyclerView.Adapter<adapterTokenlistRecycler.Viewholder> {

    ArrayList<token> tokenlist;

    public adapterTokenlistRecycler(ArrayList<token> tokenlist) {
        this.tokenlist = tokenlist;
    }



    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_tokenlist_recycler,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholder holder, int position) {
        token token=tokenlist.get(position);
        holder.tokenName.setText(token.getTokenName());
        holder.tokenNo.setText(String.valueOf(token.getTokenNo()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(v.getContext(), "", Toast.LENGTH_SHORT).show();

                Dialog tokendetailsdialog;

                tokendetailsdialog=new Dialog(v.getContext());
                ImageView closeimg;
                TextView tokenNo,tokenName,tokenPlace,tokenAge;
                tokendetailsdialog.setContentView(R.layout.tokendetailspopup);
                closeimg=tokendetailsdialog.findViewById(R.id.popupClosebuttImg);
                tokenNo= tokendetailsdialog.findViewById(R.id.popUpTokenNo);
                tokenName=tokendetailsdialog.findViewById(R.id.popUpTokenName);
                tokenAge=tokendetailsdialog.findViewById(R.id.popUpTokenAge);
                tokenPlace=tokendetailsdialog.findViewById(R.id.popUpTokenPlace);
                tokenNo.setText(String.valueOf(token.getTokenNo()));
                tokenName.setText(token.getTokenName());
                tokenAge.setText(String.valueOf(token.getTokenAge()));
                tokenPlace.setText(token.getTokenPlace());
                closeimg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tokendetailsdialog.dismiss();
                    }
                });

                tokendetailsdialog.show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return tokenlist.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{

        TextView tokenName,tokenNo;


        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            tokenName=itemView.findViewById(R.id.tokenlisttokenName);
            tokenNo=itemView.findViewById(R.id.tokenlisttokenNo);
        }
    }
}
