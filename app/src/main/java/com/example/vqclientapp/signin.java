package com.example.vqclientapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class signin extends AppCompatActivity {

    private Button signup;
    String username, passwd;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        if (SaveId.getId(signin.this).equals("defaut")) {

            //MANUAL LOGIN

            EditText UserNameView = findViewById(R.id.signinemailid);
            EditText pswd = findViewById(R.id.signinPassword);
            Button butt = findViewById(R.id.signinButton);

            auth = FirebaseAuth.getInstance();

            butt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    username = UserNameView.getText().toString();
                    passwd = pswd.getText().toString();

                    if(username.isEmpty() || passwd.isEmpty()){
                        Toast.makeText(signin.this, "UserName or Password cannot be Empty", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        int count = 0;
                        for (char c : username.toCharArray()) {
                            if (c == '@')
                                count += 1;
                        }


                        if (count > 1) {
                            //DEPT LOGIN
                            String deptuname = username.replace(".", "-");
                            FirebaseDatabase.getInstance().getReference("main").child("DepartmentCredentials").child(deptuname).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        if (snapshot.child("password").getValue().toString().equals(passwd)) {
                                            loadingScreen.startloading(signin.this, "loading");
                                            String uname;
                                            uname = snapshot.child("companyID").getValue().toString();
                                            String deptID;
                                            deptID = Objects.requireNonNull(snapshot.child("deptID").getValue()).toString();
                                            Intent deptSigninSuccessfull = new Intent(signin.this, departmenthome.class);
                                            SaveId.setId(signin.this, uname);
                                            SaveId.setDepID(signin.this, deptID);
                                            SaveId.setIsAdmin(signin.this, false);
                                            startActivity(deptSigninSuccessfull);
                                            Toast.makeText(signin.this, "Dept Signed in Successfully", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(signin.this, "Sign in Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                }
                            });
                        } else {
                            // COMPANY LOGIN
                            auth.signInWithEmailAndPassword(username, passwd).addOnCompleteListener(signin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        loadingScreen.startloading(signin.this, "loading");

                                        String id = username.replace(".", "-");
                                        Intent companySigninSuccessful = new Intent(signin.this, homePage.class);
                                        SaveId.setId(signin.this, id);
                                        SaveId.setIsAdmin(signin.this, true);
                                        startActivity(companySigninSuccessful);
                                        Toast.makeText(signin.this, "Company Signed in Successfully", Toast.LENGTH_SHORT).show();

                                        finish();
                                    } else {
                                        Toast.makeText(signin.this, "Sign in Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                }
            });


            signup = findViewById(R.id.signup);
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent signup = new Intent(signin.this, firstDetails.class);
                    startActivity(signup);
                }
            });


        }
        else {

            // AUTO LOGIN
            if(SaveId.getIsAdmin(signin.this)==true) {

                loadingScreen.startloading(signin.this,"loading");

                // COMPANY ADMIN AUTO LOGIN
                Intent companySigninSuccessful = new Intent(signin.this, homePage.class);
                startActivity(companySigninSuccessful);
                Toast.makeText(signin.this, "Company Signed in Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
            else {

                loadingScreen.startloading(signin.this,"loading");

                // DEPARTMENT AUTO LOGIN


                Intent deptSigninSuccessful = new Intent(signin.this, departmenthome.class);
                startActivity(deptSigninSuccessful);
                Toast.makeText(signin.this, "Dept Signed in Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }
}

