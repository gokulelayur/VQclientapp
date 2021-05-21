package com.example.vqclientapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class tokenlistFragment extends Fragment {

    RecyclerView recyclerView;
    adapterTokenlistRecycler adapterTokenlistRecycler;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tokenlist,container,false);
        recyclerView=view.findViewById(R.id.toklistRecycler);
        adapterTokenlistRecycler=new adapterTokenlistRecycler();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterTokenlistRecycler);

        return view;
    }
}
