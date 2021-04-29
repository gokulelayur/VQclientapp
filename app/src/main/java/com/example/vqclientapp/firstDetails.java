package com.example.vqclientapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class firstDetails extends AppCompatActivity {
    String company, cat,desc,mapLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_details);

        EditText cname = findViewById(R.id.cname);
        EditText category = findViewById(R.id.catagory);
        EditText description = findViewById(R.id.description);
        EditText map = findViewById(R.id.map);
        Button firstNext = findViewById(R.id.firstDetailsNextButton);


        firstNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                company=cname.getText().toString();
                cat=category.getText().toString();
                desc=description.getText().toString();
                mapLink=map.getText().toString();
//                Toast.makeText(firstDetails.this, "abc" +noDept, Toast.LENGTH_SHORT).show();
                if(company == null || cat == null || desc == null || mapLink == null) {

                    Toast.makeText(firstDetails.this, "Fill all details", Toast.LENGTH_SHORT).show();

                }
                else{
                    Intent firstDetailsToSecondDetails = new Intent(firstDetails.this, secondDetails.class);
                    firstDetailsToSecondDetails.putExtra("extraCompanyName", company);
                    firstDetailsToSecondDetails.putExtra("extraDescript", desc);
                    firstDetailsToSecondDetails.putExtra("extraMap", mapLink);
                    firstDetailsToSecondDetails.putExtra("extraCategory", cat);
                    startActivity(firstDetailsToSecondDetails);
                }

            }
        });

    }
}