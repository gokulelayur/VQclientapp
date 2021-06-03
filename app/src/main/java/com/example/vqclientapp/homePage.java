package com.example.vqclientapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class homePage extends AppCompatActivity implements adapterDepts.deptListListner {
    String uname;
    RecyclerView recyclerView;
    adapterDepts depAd;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    ArrayList<department> departmentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        uname=SaveId.getId(homePage.this);
        SaveId.setDepID(homePage.this, "defaut");

        recyclerView = findViewById(R.id.deptRecycler);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("main").child("company").child(uname).child("department");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration sep = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(sep);

        departmentArrayList = new ArrayList<>();

        depAd = new adapterDepts(departmentArrayList, this, this);
        recyclerView.setAdapter(depAd);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    department dep = snap.getValue(department.class);
                    departmentArrayList.add(dep);

                }
                depAd.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(homePage.this, "Unable to fetch", Toast.LENGTH_SHORT).show();
            }
        });


        Button addDept = findViewById(R.id.homeAddDept);
        addDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoadddept = new Intent(homePage.this, addDepartment.class);
//                gotoadddept.putExtra("uname", uname);
                startActivity(gotoadddept);
            }
        });
        Button companyLogout = findViewById(R.id.companyLogout);
        companyLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveId.setId(homePage.this, "defaut");
                SaveId.setIsAdmin(homePage.this,false);
                Intent goBackToSignin = new Intent(homePage.this, signin.class);
                startActivity(goBackToSignin);
            }
        });

        Toast.makeText(this, "dept ID set to "+SaveId.getDepID(this), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeptListClick(int position) {
        Intent deptListClicked = new Intent(homePage.this, departmenthome.class);
        SaveId.setDepID(homePage.this,departmentArrayList.get(position).getName());
        startActivity(deptListClicked);
    }
}