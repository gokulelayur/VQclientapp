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

import com.example.vqclientapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class homeFragment extends Fragment {

    RecyclerView recyclerView;
    adapterQueueRecycler adapterQueueRecycler;
//    ArrayList<bufferItem> queue;
//    FirebaseDatabase rootNode;
//    DatabaseReference reference;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);

        recyclerView=view.findViewById(R.id.queueRecycler);
        adapterQueueRecycler=new adapterQueueRecycler();

//        rootNode=FirebaseDatabase.getInstance();
//        reference=rootNode.getReference("main").child("company").child(SaveId.getId(getContext())).child("department").child(SaveId.getDepID(getContext())).child("BUFFER");
//        reference.orderByKey().addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot:snapshot.getChildren()
//                     ) {
//                    bufferItem bufferItem=dataSnapshot.getValue();
//
//                    queue.add()
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterQueueRecycler);


        return view;
    }
}
