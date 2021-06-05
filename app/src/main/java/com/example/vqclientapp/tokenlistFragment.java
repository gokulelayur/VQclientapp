package com.example.vqclientapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class tokenlistFragment extends Fragment {

    RecyclerView recyclerView;
    adapterTokenlistRecycler adapterTokenlistRecycler;
    ArrayList<token> tokenArrayList;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String uname, depID;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tokenlist, container, false);

        loadingScreen.startloading(getActivity(),"Fetching data");            //LOADING SCREEN STARTED

        recyclerView = view.findViewById(R.id.toklistRecycler);


        tokenArrayList = new ArrayList<>();
        uname = SaveId.getId(getContext());
        depID = SaveId.getDepID(getContext());
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("main").child("company").child(uname).child("department").child(depID).child("TOKEN");


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        adapterTokenlistRecycler = new adapterTokenlistRecycler(tokenArrayList);
        recyclerView.setAdapter(adapterTokenlistRecycler);


        reference.orderByChild("tokenNo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for (DataSnapshot snap : snapshot.getChildren()) {
                    token token = snap.getValue(token.class);
                    tokenArrayList.add(token);
                }
                adapterTokenlistRecycler.notifyDataSetChanged();
                loadingScreen.stoploading();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        return view;
    }
}
