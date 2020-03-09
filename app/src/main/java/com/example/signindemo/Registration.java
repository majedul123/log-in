package com.example.signindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {
    EditText et_email, et_password;
    Button bt_registration;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        et_email = findViewById(R.id.email);
        et_password = findViewById(R.id.password);
        bt_registration = findViewById(R.id.btnRegistration);
        progressBar=findViewById(R.id.progressbarid);


        bt_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration();

                //startActivity(new Intent(Registration.this, Signin.class));
            }
        });


    }

    private void registration() {

        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Email empty", Toast.LENGTH_SHORT).show();
            et_email.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Password empty", Toast.LENGTH_SHORT).show();
            et_password.requestFocus();
            return;
        }
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(getApplicationContext(), "Enter a valid email", Toast.LENGTH_SHORT).show();
            et_email.requestFocus();
            return;

        }
        if (password.length()<6) {
            Toast.makeText(getApplicationContext(), "Password must be greater than 6 digit", Toast.LENGTH_SHORT).show();
            et_password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information

                    Toast.makeText(getApplicationContext(), "register is successful", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "User already registered", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    progressBar.setVisibility(View.GONE);
                    // Toast.makeText(getApplicationContext(),"register is not successful",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
