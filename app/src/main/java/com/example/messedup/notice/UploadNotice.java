package com.example.messedup.notice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.messedup.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UploadNotice extends AppCompatActivity {

    EditText addNoticeText, addNoticeDesc;
    CardView selectNoticeImage;
    Button uploadNoticeBtn;

    private String imageurl;
    private Uri imageUri;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private FirebaseAuth fAuth;
    static String name;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice2);

        addNoticeText = findViewById(R.id.notice_title_text);
        addNoticeDesc = findViewById(R.id.notice_desc_text);
        selectNoticeImage = findViewById(R.id.select_image_card);
        uploadNoticeBtn = findViewById(R.id.send_notice_btn);

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        fAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(UploadNotice.this);
        dialog.setTitle("Upload status");
        dialog.setMessage("Uploading notice...");

        selectNoticeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
            }
        });

        uploadNoticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addNoticeText.getText().toString().isEmpty()) {
                    addNoticeText.setError("Title is requited");
                    addNoticeText.requestFocus();
                    return;
                }

                dialog.show();

                Calendar calendarDate = Calendar.getInstance();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
                String date = currentDate.format(calendarDate.getTime());

                Calendar calendarTime = Calendar.getInstance();
                SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
                String time = currentTime.format(calendarTime.getTime());

                DatabaseReference databaseReference = database.getReference().child("notices");

                if (imageUri != null) {
                    // folder name,            // image name
                    StorageReference storageReference = storage.getReference().child("noticeImages").child("notice" + date + "-" + time);

                    storageReference.putFile(imageUri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            imageurl = uri.toString();


                                            // get user admin name
                                            FirebaseDatabase.getInstance().getReference().child("adminUser").child(fAuth.getCurrentUser().getUid())
                                                    .addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            name = snapshot.child("name").getValue(String.class);

                                                            Notice notice = new Notice(name, date, time, addNoticeText.getText().toString(), addNoticeDesc.getText().toString(), imageurl);
                                                            databaseReference.push().setValue(notice).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void unused) {
                                                                    dialog.dismiss();
                                                                    Toast.makeText(UploadNotice.this, "Notice uploaded successfully.", Toast.LENGTH_SHORT).show();
                                                                    startActivity(new Intent(UploadNotice.this, ManageNotice.class));
                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    dialog.dismiss();
                                                                    Log.d("jayant", "database error: " + e.toString());
                                                                    Toast.makeText(UploadNotice.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                            dialog.dismiss();
                                                            Log.d("jayant", error.toString());
                                                            Toast.makeText(UploadNotice.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    });


                                        }
                                    });

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismiss();
                            Log.d("jayant", "storage error: " + e.toString());
                            Toast.makeText(UploadNotice.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {

                    Notice notice = new Notice("jay", date, time, addNoticeText.getText().toString(), addNoticeDesc.getText().toString());
                    databaseReference.push().setValue(notice).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            dialog.dismiss();
                            Toast.makeText(UploadNotice.this, "Notice send successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismiss();
                            Log.d("jayant", "database error: " + e.toString());
                            Toast.makeText(UploadNotice.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }


            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && data != null) {
            imageUri = data.getData();
//            signUpImage.setImageURI(imageUri);
        }
    }
}