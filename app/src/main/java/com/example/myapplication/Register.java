package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity {
    private TextView txtLogIn;
    private EditText etFullName,etEmail,etPassword,etaddressNumber;
    private Button btnRegister;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etFullName=(EditText)findViewById(R.id.etFullName);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etaddressNumber=(EditText)findViewById(R.id.etaddressNumber);
        btnRegister=(Button) findViewById(R.id.btnRegister);
        txtLogIn=(TextView) findViewById(R.id.txtLogIn);

        firebaseAuth= FirebaseAuth.getInstance();

        txtLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullName = etFullName.getText().toString().trim();
                final String email = etEmail.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();
                final String address = etaddressNumber.getText().toString().trim();
                try
                {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sendUserData(fullName, email, password, address);
                            Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_LONG).show();
                            String username=email;
                            Intent intent = new Intent(Register.this,ActivityWelcome.class);
                            intent.putExtra("user",username);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                }
                catch (IllegalArgumentException e)
                {
                    Toast.makeText(getApplicationContext(), "Illegal Arguments", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void sendUserData(String fullName, String email, String password, String address) {
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference("Users/"+ firebaseAuth.getUid()+"/UserData");
        User user=new User(fullName,email,password,address);
        ref.setValue(user);
    }
}