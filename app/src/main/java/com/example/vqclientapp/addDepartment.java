package com.example.vqclientapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addDepartment extends AppCompatActivity {
    String name, descript, st, et, uname;
    int maxtokens = -1, avgt = -1;
    FirebaseDatabase rootNode;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_department);
        EditText dname = findViewById(R.id.deptName);
        EditText desc = findViewById(R.id.deptDescription);
        EditText maxtok = findViewById(R.id.maxTokens);
        EditText stime = findViewById(R.id.startTime);
        EditText etime = findViewById(R.id.endtime);
        EditText avgtime = findViewById(R.id.avgtime);
        Button donebutt = findViewById(R.id.doneDeptDetailsbutt);
        Button addmorebutt = findViewById(R.id.addMoreDeptButt);

        Bundle extras;
        if (savedInstanceState == null) {
            extras = getIntent().getExtras();
            if (extras == null) {
                uname = null;
            } else {
                uname = extras.getString("uname");
            }
        }


        donebutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = dname.getText().toString();
                descript = desc.getText().toString();
                maxtokens = Integer.parseInt(maxtok.getText().toString());
                st = stime.getText().toString();
                et = etime.getText().toString();
                avgt = Integer.parseInt(avgtime.getText().toString());
                if (isvalid(name, descript, maxtokens, st, et, avgt)) {
                    department help = new department(name, descript, maxtokens, st, et, avgt);
                    rootNode = FirebaseDatabase.getInstance();
                    ref = rootNode.getReference("main").child("unverified").child(uname);
                    ref.child(name).setValue(help).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(addDepartment.this, "Department added successfully", Toast.LENGTH_SHORT).show();
                        }
                    });


                    Intent it = new Intent(addDepartment.this, homePage.class);
                    it.putExtra("uname", uname);
                    startActivity(it);
                    finish();

                } else {
                    Toast.makeText(addDepartment.this, "Enter all the details", Toast.LENGTH_SHORT).show();
                }

            }
        });
        addmorebutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = dname.getText().toString();
                descript = desc.getText().toString();
                maxtokens = Integer.parseInt(maxtok.getText().toString());
                st = stime.getText().toString();
                et = etime.getText().toString();
                avgt = Integer.parseInt(avgtime.getText().toString());
                if (isvalid(name, descript, maxtokens, st, et, avgt)) {
                    department help = new department(name, descript, maxtokens, st, et, avgt);
                    Toast.makeText(addDepartment.this, "currenttok: ", Toast.LENGTH_SHORT).show();
                    rootNode = FirebaseDatabase.getInstance();
                    ref = rootNode.getReference("main").child("unverified").child(uname);
                    ref.child(name).setValue(help).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(addDepartment.this, "Department added successfully", Toast.LENGTH_SHORT).show();
                        }
                    });


                    Intent it = new Intent(addDepartment.this, addDepartment.class);
                    it.putExtra("uname", uname);
                    startActivity(it);
                    finish();
                } else {
                    Toast.makeText(addDepartment.this, "Enter all the details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isvalid(String name, String descript, int maxtokens, String st, String et, int avgt) {
        if (name != "" && descript != "" && maxtokens != -1 && st != "" && et != "" && avgt != -1) {
            return (true);
        } else {
            return false;
        }
    }
}