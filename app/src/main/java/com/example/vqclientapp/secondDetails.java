package com.example.vqclientapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class secondDetails extends AppCompatActivity {
    String company, cat, desc, mapLink;
    String eid, psswd1, psswd2, mobNo, uname;
    Boolean check;
    private FirebaseAuth auth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_details);


        EditText email = findViewById(R.id.userEmailId);
        EditText ps1 = findViewById(R.id.password);
        EditText ps2 = findViewById(R.id.confirmPassword);
        EditText mobNumber = findViewById(R.id.mobileNumber);
        Button butt = findViewById(R.id.signUpBtn);
        CheckBox cbox = findViewById(R.id.termsAndConditionsCheckbox);
        auth = FirebaseAuth.getInstance();


        Bundle extras;
        if (savedInstanceState == null) {
            extras = getIntent().getExtras();
            if (extras == null) {
                company = null;
                cat = null;
                desc = null;
                mapLink = null;
            } else {
                company = extras.getString("extraCompanyName");
                cat = extras.getString("extraCategory");
                desc = extras.getString("extraDescript");
                mapLink = extras.getString("extraMap");
            }
        }

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eid = email.getText().toString();
                psswd1 = ps1.getText().toString();
                psswd2 = ps2.getText().toString();
                mobNo = mobNumber.getText().toString();
                check = cbox.isChecked();
//                Toast.makeText(secondDetails.this, "hihihi", Toast.LENGTH_SHORT).show();
                uname = emailToUname(eid);

                if (isValid(eid, psswd1, psswd2, mobNo)) {

                    signuphelper signupHelp = new signuphelper(uname, cat, company, desc, mapLink, mobNo);
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("main");
                    reference.child("company").child(uname).setValue(signupHelp);

                    auth.createUserWithEmailAndPassword(eid, psswd1).addOnCompleteListener(secondDetails.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Intent signupSuccessful = new Intent(secondDetails.this, homePage.class);
                                signupSuccessful.putExtra("uname", uname);
                                startActivity(signupSuccessful);
                                finish();
                            } else {
                                Toast.makeText(secondDetails.this, "User creation Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }

    private String emailToUname(String eid) {
        String temp;
        temp = eid.replace("@", "0");
        temp = temp.replace(".", "1");
//        Toast.makeText(this, temp, Toast.LENGTH_SHORT).show();
        return temp;
    }

    private boolean isValid(String eid, String ps1, String ps2, String mobNo) {
        if (ps1.equals(ps2)) {
            if (ps1.length() >= 8) {
                if (mobNo.length() == 10) {
                    if (isEmailValid(eid)) {
                        if (check) {
                            return true;
                        } else {
                            Toast.makeText(this, "Agree to Terms and Conditions", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(this, "Email Invalid", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Invalid Mobile number", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Password is too Short", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private boolean isEmailValid(String eid) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(eid).matches();
    }
}