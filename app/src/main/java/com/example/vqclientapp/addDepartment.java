package com.example.vqclientapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addDepartment extends AppCompatActivity {
    String name,descript,maxtokens,st,et,avgt,uname;
    FirebaseDatabase rootNode;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_department);
        EditText dname = findViewById(R.id.deptName);
        EditText desc=findViewById(R.id.deptDescription);
        EditText maxtok=findViewById(R.id.maxTokens);
        EditText stime=findViewById(R.id.startTime);
        EditText etime=findViewById(R.id.endtime);
        EditText avgtime=findViewById(R.id.avgtime);
        Button donebutt=findViewById(R.id.doneDeptDetailsbutt);
        Button addmorebutt=findViewById(R.id.addMoreDeptButt);

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


        donebutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=dname.getText().toString();
                descript=desc.getText().toString();
                maxtokens=maxtok.getText().toString();
                st=stime.getText().toString();
                et=etime.getText().toString();
                avgt=avgtime.getText().toString();
                if(isvalid(name,descript,maxtokens,st,et,avgt)){
                    deptdetailshelper help=new deptdetailshelper(name,descript,maxtokens,st,et,avgt);
                    rootNode=FirebaseDatabase.getInstance();
                    ref=rootNode.getReference("main").child("company").child(uname).child("department");
                    ref.child(name).setValue(help);
                    Intent it=new Intent(addDepartment.this,homePage.class);
                    it.putExtra("uname",uname);
                    startActivity(it);
                    finish();

                }
                else{
                    Toast.makeText(addDepartment.this, "Enter all the details", Toast.LENGTH_SHORT).show();
                }

            }
        });
        addmorebutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=dname.getText().toString();
                descript=desc.getText().toString();
                maxtokens=maxtok.getText().toString();
                st=stime.getText().toString();
                et=etime.getText().toString();
                avgt=avgtime.getText().toString();
                if(isvalid(name,descript,maxtokens,st,et,avgt)){
                    deptdetailshelper help=new deptdetailshelper(name,descript,maxtokens,st,et,avgt);
                    rootNode=FirebaseDatabase.getInstance();
                    ref=rootNode.getReference("main").child("company").child(uname).child("department");
                    ref.child(name).setValue(help);
                    Intent it=new Intent(addDepartment.this,addDepartment.class);
                    it.putExtra("uname",uname);
                    startActivity(it);
                    finish();
                }
                else{
                    Toast.makeText(addDepartment.this, "Enter all the details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isvalid(String name, String descript, String maxtokens, String st, String et, String avgt) {
        if(name!=null && descript!=null && maxtokens!=null && st!=null && et!=null && avgt!=null){
            return(true);
        }
        else{
            return false;
        }
    }
}