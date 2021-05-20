package com.example.vqclientapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class testactivitity extends AppCompatActivity {
    department department;
    String uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testactivitity);
        TextView deptName=findViewById(R.id.deptHomeName);

        department= (com.example.vqclientapp.department) getIntent().getSerializableExtra("passdep");
        uname=getIntent().getStringExtra("uname");
        deptName.setText(department.getName());
    }
}