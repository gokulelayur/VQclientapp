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

public class signup extends AppCompatActivity {

    String eid,psswd1,psswd2;
    Boolean check;

    private FirebaseAuth auth;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText email=findViewById(R.id.userEmailId);
        EditText ps1=findViewById(R.id.password);
        EditText ps2=findViewById(R.id.confirmPassword);
        Button butt=findViewById(R.id.signUpBtn);
        CheckBox cbox=findViewById(R.id.termsAndConditionsCheckbox);
        auth=FirebaseAuth.getInstance();

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eid= email.getText().toString();
                psswd1=ps1.getText().toString();
                psswd2=ps2.getText().toString();
                check=cbox.isChecked();

                if(isValid(eid,psswd1,psswd2)){

                    auth.createUserWithEmailAndPassword(eid,psswd1).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent signupSuccessful =new Intent(signup.this,homePage.class);
                                startActivity(signupSuccessful);
                                finish();
                            }
                            else{
                                Toast.makeText(signup.this, "User creation Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }

    private boolean isValid(String eid, String ps1, String ps2) {
        if(ps1.equals(ps2)){
            if (ps1.length()>=8){
                if(isEmailValid(eid)){
                    if(check){
                        return true;
                    }
                    else{
                        Toast.makeText(this, "Agree to Terms and Conditions", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(this, "Email Invalid", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Password is too Short", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Password Mismatc", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private boolean isEmailValid(String eid) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(eid).matches();
    }


}