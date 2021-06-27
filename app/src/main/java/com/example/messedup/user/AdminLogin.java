package com.example.messedup.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.messedup.R;
import com.example.messedup.dashboard.HomeActivity;
import com.example.messedup.notice.ManageNotice;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {

    private EditText adminEmailText, adminPasswordText;
    private Button adminLoginBtn;
    private ProgressBar progressBar;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adminEmailText = findViewById(R.id.admin_email_text);
        adminPasswordText = findViewById(R.id.admin_password_text);
        adminLoginBtn = findViewById(R.id.admin_login_btn);
        progressBar = findViewById(R.id.login_progressBar);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            progressBar.setVisibility(View.GONE);
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

                progressBar.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(adminEmailText.getText().toString(), adminPasswordText.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(AdminLogin.this, "Signin successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AdminLogin.this, HomeActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("jayant", e.toString());
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(AdminLogin.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}