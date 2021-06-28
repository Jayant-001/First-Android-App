package com.example.messedup.ebooks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.messedup.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UploadEbooks extends AppCompatActivity {

    TextView showBranch, showSubject, pdfName;
    private Button selectPdf, uploadPdf;
    private EditText pdfTitle;
    String branch, subject;

    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_ebooks);

        showBranch = findViewById(R.id.show_branch);
        showSubject = findViewById(R.id.show_subject);

        pdfName = findViewById(R.id.show_pdf_name);
        pdfTitle = findViewById(R.id.pdf_title);
        selectPdf = findViewById(R.id.select_btn);
        uploadPdf = findViewById(R.id.upload_btn);

        databaseReference = FirebaseDatabase.getInstance().getReference("college_books");
        storageReference = FirebaseStorage.getInstance().getReference("college_books");

        branch = getIntent().getStringExtra("branch");
        subject = getIntent().getStringExtra("subject");

        showBranch.setText(branch);
        showSubject.setText(subject);

        uploadPdf.setEnabled(false);


        selectPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPdfData();
            }
        });

    }

    public void selectPdfData() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "SELECT PDF FILE"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uploadPdf.setEnabled(true);
            String fileName = getFileName(data.getData());
            pdfName.setText(fileName);

            uploadPdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(pdfTitle.getText().toString().equals("")) {
                        pdfTitle.setError("Title is required");
                        pdfTitle.requestFocus();
                        return;
                    }

                    uploadPdfToFirebase(data.getData());

                }
            });

        }
    }

    private void uploadPdfToFirebase(Uri data) {

        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("File is uploading...");
        pd.setCancelable(false);
        pd.show();

        Calendar calendarDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yy");
        String date = currentDate.format(calendarDate.getTime());

        Calendar calendarTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        String time = currentTime.format(calendarTime.getTime());


        StorageReference reference = storageReference.child(pdfTitle.getText().toString() + date + "-" + time);
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri uri = uriTask.getResult();

                FirebaseAuth fAuth = FirebaseAuth.getInstance();
                FirebaseDatabase.getInstance().getReference().child("adminUser").child(fAuth.getCurrentUser().getUid())
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String name = snapshot.child("name").getValue(String.class);

                                PdfData pdfData = new PdfData(pdfTitle.getText().toString(), uri.toString(), date, branch, name);

                                databaseReference.child(branch).child(subject).push().setValue(pdfData)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                pd.dismiss();
                                                Toast.makeText(UploadEbooks.this, "Ebook uploaded successfully", Toast.LENGTH_SHORT).show();
                                                finish();

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        pd.dismiss();
                                        Log.d("jayant", "database error: " + e.getMessage());
                                        Toast.makeText(UploadEbooks.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                pd.dismiss();
                                Log.d("jayant", error.toString());
                                Toast.makeText(UploadEbooks.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                pd.setMessage("Uploading Progress..." + (int) progress + "%");
            }
        });



    }



















    private String getFileName(Uri uri) throws IllegalArgumentException {
        // Obtain a cursor with information regarding this uri
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor.getCount() <= 0) {
            cursor.close();
            throw new IllegalArgumentException("Can't obtain file name, cursor is empty");
        }

        cursor.moveToFirst();

        String fileName = cursor.getString(cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME));

        cursor.close();

        return fileName;
    }

}