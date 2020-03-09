package com.example.signindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signin extends AppCompatActivity {
    EditText et_email, et_password;
    Button bt_login, bt_signup;

    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();
        et_email = findViewById(R.id.email);
        et_password = findViewById(R.id.password);
        bt_login = findViewById(R.id.btnLogin);
        bt_signup = findViewById(R.id.btnLinkToRegisterScreen);
        progressBar=findViewById(R.id.progressbarid);

        mAuth=FirebaseAuth.getInstance();

        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signin.this, Registration.class));
            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });

    }

    private void signin() {

        String email = et_email.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), " email empty", Toast.LENGTH_SHORT).show();
            et_email.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), " password empty", Toast.LENGTH_SHORT).show();
            et_password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                      startActivity(new Intent(Signin.this,ProfileActivity.class));

                }
                else {
                    Toast.makeText(getApplicationContext(), "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
}
