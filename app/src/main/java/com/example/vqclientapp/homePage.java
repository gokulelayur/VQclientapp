package com.example.vqclientapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homePage extends AppCompatActivity {
    String uname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        Bundle extras;
        if(savedInstanceState == null){
            extras =getIntent().getExtras();
            if(extras==null){
                uname=null;
            }
            else{
                uname=extras.getString("uname");
            }
        };
        Button addDept = findViewById(R.id.homeAddDept);
        addDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homePage.this, addDepartment.class);
                intent.putExtra("uname",uname);
                startActivity(intent);
            }
        });
    }
}