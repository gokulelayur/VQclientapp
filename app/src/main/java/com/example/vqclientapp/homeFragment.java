package com.example.vqclientapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class homeFragment extends Fragment {

    RecyclerView recyclerView;
    adapterQueueRecycler adapterQueueRecycler;

    token currentToken=new token();
    ArrayList<Integer> queue = new ArrayList<>();
    ArrayList<token> tokenArrayList = new ArrayList<>();

    FirebaseDatabase rootNode;
    DatabaseReference reference, thisDeptRef, ref;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView currentTokenDisp=view.findViewById(R.id.display_current_token);
        recyclerView = view.findViewById(R.id.queueRecycler);
        adapterQueueRecycler = new adapterQueueRecycler(tokenArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapterQueueRecycler);

        rootNode = FirebaseDatabase.getInstance();
        thisDeptRef = rootNode.getReference("main").child("company").child(SaveId.getId(getContext()))
                .child("department").child(SaveId.getDepID(getContext()));
        reference = thisDeptRef.child("BUFFER");
        ref = thisDeptRef.child("TOKEN");


        reference.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    int Value = Integer.valueOf(dataSnapshot.getValue().toString());
                    queue.add(Value);

                }
                for (int i = 0; i < queue.size(); i++) {
                    Query query = ref.orderByChild("tokenNo").equalTo(queue.get(i));
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snap) {
                            for (DataSnapshot shot : snap.getChildren()) {
                                token newtoken = shot.getValue(token.class);
                                tokenArrayList.add(newtoken);
                            }
                            currentToken=tokenArrayList.get(0);
                            currentTokenDisp.setText(String.valueOf(currentToken.getTokenNo()));
                            adapterQueueRecycler.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return view;
    }
}
