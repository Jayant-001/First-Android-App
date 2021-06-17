package com.example.messedup.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.messedup.R;
import com.example.messedup.dashboard.HomeActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class AdminSignup extends AppCompatActivity {

    private EditText userName, userEmail, userPassword, userConfPassword;
    private Button signupBtn;

    private FirebaseAuth fAuth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);

        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_password);
        userConfPassword = findViewById(R.id.user_conf_pass);
        signupBtn = findViewById(R.id.signup_btn);

        fAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = userName.getText().toString();
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();
                String confPass = userConfPassword.getText().toString();


                if(name.length() == 0) {
                    userName.setError("Name is required");
                    userName.requestFocus();
                    return;
                }

                if(email.length() == 0) {
                    userEmail.setError("Email is required");
                    userEmail.requestFocus();
                    return;
                }

                if(password.length() == 0) {
                    userPassword.setError("Password is required");
                    userPassword.requestFocus();
                    return;
                }

                if(confPass.length() == 0) {
                    userConfPassword.setError("Confirm password is required");
                    userConfPassword.requestFocus();
                    return;
                }

                if (password.compareTo(confPass) != 0) {
                    userConfPassword.setError("Password doesn't match");
                    userConfPassword.requestFocus();
                    return;
                }

                Toast.makeText(AdminSignup.this, "Data verified", Toast.LENGTH_SHORT).show();

                fAuth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

//                        Map<String, Object> data = new HashMap<>();
//                        data.put("name", name);
//                        data.put("email", email);
//                        data.put("password", password);

                        User user = new User(name, email, password);

                        database.getReference().child("adminUser").child(fAuth.getCurrentUser().getUid()).setValue(user)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(AdminSignup.this, "Account created", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AdminSignup.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                Log.d("jayant", e.toString());
                            }

                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdminSignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("jayant", "Signup error : " + e.toString());
                    }
                });


            }
        });

    }
}