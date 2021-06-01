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

public class signin extends AppCompatActivity {

    private TextView signup;
    String eid, passwd, uname;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        if (SaveId.getId(signin.this).equals("defaut")) {

            EditText email = findViewById(R.id.signinemailid);
            EditText pswd = findViewById(R.id.signinPassword);
            Button butt = findViewById(R.id.signinButton);

            auth = FirebaseAuth.getInstance();

            butt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eid = email.getText().toString();
                    passwd = pswd.getText().toString();
                    auth.signInWithEmailAndPassword(eid, passwd).addOnCompleteListener(signin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                uname = eid.replace("@", "0");
                                uname = uname.replace(".", "1");
                                Intent signinsuccessful = new Intent(signin.this, homePage.class);
                                signinsuccessful.putExtra("uname", uname);
                                startActivity(signinsuccessful);
                                Toast.makeText(signin.this, "Success", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(signin.this, "Sign in Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });


            signup = (TextView) findViewById(R.id.signup);
            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    opensignuppage();
                }
            });


        } else {
            Intent signinsuccessful = new Intent(signin.this, homePage.class);
            signinsuccessful.putExtra("uname", SaveId.getId(signin.this));
            startActivity(signinsuccessful);
            Toast.makeText(signin.this, "Success", Toast.LENGTH_SHORT).show();
            finish();

        }

    }

    public void opensignuppage() {
        Intent itt = new Intent(this, firstDetails.class);
        startActivity(itt);
    }
}

