package com.example.vqclientapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class addDepartment extends AppCompatActivity {
    String deptID, ogname, descript, st, et, uname,email, pword, confPword;
    int maxtokens = -1, avgt = -1;
    FirebaseDatabase rootNode;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_department);

        EditText ogDeptName = findViewById(R.id.ogDeptName);
        EditText desc = findViewById(R.id.deptDescription);
        EditText maxtok = findViewById(R.id.maxTokens);
        EditText stime = findViewById(R.id.startTime);
        EditText etime = findViewById(R.id.endtime);
        EditText avgtime = findViewById(R.id.avgtime);
        EditText dname = findViewById(R.id.deptID);   //this is actually id
        EditText password = findViewById(R.id.deptPassword);
        EditText ConfirmPass = findViewById(R.id.deptConfirmPassword);
        Button donebutt = findViewById(R.id.doneDeptDetailsbutt);
        Button addmorebutt = findViewById(R.id.addMoreDeptButt);

        uname = SaveId.getId(addDepartment.this);

        donebutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deptID = dname.getText().toString();   // its actually id
                ogname = ogDeptName.getText().toString();  //its the real department name
                descript = desc.getText().toString();
                maxtokens = Integer.parseInt(maxtok.getText().toString());
                st = stime.getText().toString();
                et = etime.getText().toString();
                avgt = Integer.parseInt(avgtime.getText().toString());

                pword = password.getText().toString();
                confPword = ConfirmPass.getText().toString();







                if (isvalid(deptID, descript, maxtokens, st, et, avgt, pword, confPword)) {

                    if (pword.equals(confPword)) {
                        if (pword.length() >= 8) {
                            if (!deptID.contains(" ")) {


                                FirebaseDatabase.getInstance().getReference("main").child("company").child(uname).child("department")
                                        .orderByKey().equalTo(deptID).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                        if(snapshot.exists()){

                                            //ID ALREADY EXIST
                                            Toast.makeText(addDepartment.this, "Dept ID Already Exist", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            // NEW ID ADD DEPT

                                            department help = new department(deptID, ogname, descript, maxtokens, st, et, avgt);

                                            String deptUserName=deptID+"@"+SaveId.getId(addDepartment.this);
                                            deptCredentials credentials=new deptCredentials(deptUserName,pword,deptID,SaveId.getId(addDepartment.this));

                                            rootNode = FirebaseDatabase.getInstance();
                                            ref = rootNode.getReference("main").child("unverified").child(uname);
                                            ref.child(deptID).setValue(help).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {

                                                    ref.child(deptID).child("Credentials").setValue(credentials).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Toast.makeText(addDepartment.this, "Department details send for Verification", Toast.LENGTH_SHORT).show();

                                                        }
                                                    });
                                                }
                                            });


                                            Intent it = new Intent(addDepartment.this, homePage.class);
                                            startActivity(it);
                                            finish();

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                    }
                                });

                            }
                            else {
                                Toast.makeText(addDepartment.this, "ID shouldn't contain spaces ", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(addDepartment.this, "Password is too Short", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(addDepartment.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(addDepartment.this, "Enter all the details", Toast.LENGTH_SHORT).show();
                }
            }
        });







        addmorebutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deptID = dname.getText().toString();   // its actually id
                ogname = ogDeptName.getText().toString();  //its the real department name
                descript = desc.getText().toString();
                maxtokens = Integer.parseInt(maxtok.getText().toString());
                st = stime.getText().toString();
                et = etime.getText().toString();
                avgt = Integer.parseInt(avgtime.getText().toString());

                pword = password.getText().toString();
                confPword = ConfirmPass.getText().toString();







                if (isvalid(deptID, descript, maxtokens, st, et, avgt, pword, confPword)) {

                    if (pword.equals(confPword)) {
                        if (pword.length() >= 8) {
                            if (!deptID.contains(" ")) {


                                FirebaseDatabase.getInstance().getReference("main").child("company").child(uname).child("department")
                                        .orderByKey().equalTo(deptID).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                        if(snapshot.exists()){

                                            //ID ALREADY EXIST
                                            Toast.makeText(addDepartment.this, "Dept ID Already Exist", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            // NEW ID ADD DEPT

                                            department help = new department(deptID, ogname, descript, maxtokens, st, et, avgt);

                                            String deptUserName=deptID+"@"+SaveId.getId(addDepartment.this);
                                            deptCredentials credentials=new deptCredentials(deptUserName,pword,deptID,SaveId.getId(addDepartment.this));

                                            rootNode = FirebaseDatabase.getInstance();
                                            ref = rootNode.getReference("main").child("unverified").child(uname);
                                            ref.child(deptID).setValue(help).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {

                                                    ref.child(deptID).child("Credentials").setValue(credentials).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Toast.makeText(addDepartment.this, "Department details send for Verification", Toast.LENGTH_SHORT).show();

                                                        }
                                                    });
                                                }
                                            });


                                            Intent it = new Intent(addDepartment.this, homePage.class);
                                            startActivity(it);
                                            finish();

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                    }
                                });

                            }
                            else {
                                Toast.makeText(addDepartment.this, "ID shouldn't contain spaces ", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(addDepartment.this, "Password is too Short", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(addDepartment.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(addDepartment.this, "Enter all the details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isvalid(String name, String descript, int maxtokens, String st, String et, int avgt, String pword, String confPword) {
        if (name != "" && descript != "" && maxtokens != -1 && st != "" && et != "" && avgt != -1 && pword != "" && confPword != "") {
//            Query query=FirebaseDatabase.getInstance().getReference("main/company")
            return (true);
        } else {
            return false;
        }
    }
}

