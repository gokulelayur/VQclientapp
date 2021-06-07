package com.example.vqclientapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class homePage extends AppCompatActivity implements adapterDepts.deptListListner {
    String uname;
    RecyclerView recyclerView;
    adapterDepts depAd;
    FirebaseDatabase rootNode;
    DatabaseReference reference, compRef;
    ArrayList<department> departmentArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        loadingScreen.startloading(homePage.this, "loading");        //LOADING SCREEN START

        uname = SaveId.getId(homePage.this);
        SaveId.setDepID(homePage.this, "defaut");


        recyclerView = findViewById(R.id.deptRecycler);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("main").child("company").child(uname).child("department");
        compRef = rootNode.getReference("main/company").child(uname);


        TextView compName, compDesc, compCat;
        compName = findViewById(R.id.profileCName);
        compCat = findViewById(R.id.profileCat);
        compDesc = findViewById(R.id.profileDesc);
        compRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                compName.setText(Objects.requireNonNull(snapshot.child("company").getValue()).toString());
                compCat.setText(Objects.requireNonNull(snapshot.child("cat").getValue()).toString());
                compDesc.setText(Objects.requireNonNull(snapshot.child("desc").getValue()).toString());

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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

                loadingScreen.stoploading();   //LOADING SCREEN STOP
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(homePage.this, "Unable to fetch", Toast.LENGTH_SHORT).show();
            }
        });


        ImageView addDept = findViewById(R.id.homeAddDept);
        addDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoadddept = new Intent(homePage.this, addDepartment.class);
                startActivity(gotoadddept);
            }
        });
        ImageView companyLogout = findViewById(R.id.companyLogout);
        companyLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveId.setId(homePage.this, "defaut");
                SaveId.setIsAdmin(homePage.this, false);
                Intent goBackToSignin = new Intent(homePage.this, signin.class);
                startActivity(goBackToSignin);
                Toast.makeText(homePage.this, "Logging out", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onDeptListClick(int position) {
        Intent deptListClicked = new Intent(homePage.this, departmenthome.class);
        SaveId.setDepID(homePage.this, departmentArrayList.get(position).getName());
        startActivity(deptListClicked);
    }
}