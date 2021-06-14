package com.example.messedup.notice;
import com.example.messedup.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class AddNotice extends AppCompatActivity {

    private EditText noticeTitle, noticeDesc;
    private Button saveNotice;
    private FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);

        noticeTitle = findViewById(R.id.notice_title);
        noticeDesc = findViewById(R.id.notice_desc);
        saveNotice = findViewById(R.id.save_notice);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        saveNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = noticeTitle.getText().toString();
                String desc = noticeDesc.getText().toString();

                if (title.isEmpty()) {
                    noticeTitle.setError("This field is required.");
                    noticeTitle.requestFocus();
                    return;
                }
                if(desc.isEmpty()) {
                    noticeDesc.setError("This field is required");
                    noticeDesc.requestFocus();
                    return;
                }

                Map<Object, String> data = new HashMap<>();
                data.put("title", title);
                data.put("desc", desc);

                reference.child("notices").push().setValue(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(AddNotice.this, "Notice saved.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddNotice.this, e.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("notice", e.toString());
                    }
                });

            }
        });
    }
}