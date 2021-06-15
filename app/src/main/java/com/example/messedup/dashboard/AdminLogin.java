package com.example.messedup.dashboard;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {

    private EditText adminEmailText, adminPasswordText;
    private Button adminLoginBtn;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adminEmailText = findViewById(R.id.admin_email_text);
        adminPasswordText = findViewById(R.id.admin_password_text);
        adminLoginBtn = findViewById(R.id.admin_login_btn);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(AdminLogin.this, ManageNotice.class));
            finish();
        }

        adminLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adminEmailText.getText().toString().isEmpty()) {
                    adminEmailText.setError("This field is required.");
                    adminEmailText.requestFocus();
                    return;
                }
                if(adminPasswordText.getText().toString().isEmpty()) {
                    adminPasswordText.setError("This field is required.");
                    adminPasswordText.requestFocus();
                    return;
                }

                fAuth.signInWithEmailAndPassword(adminEmailText.getText().toString(), adminPasswordText.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(AdminLogin.this, "Signin successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AdminLogin.this, ManageNotice.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("jayant", e.toString());
                        Toast.makeText(AdminLogin.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}