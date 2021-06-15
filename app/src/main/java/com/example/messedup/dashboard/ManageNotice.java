package com.example.messedup.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.messedup.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ManageNotice extends AppCompatActivity {

    FloatingActionButton addNoticeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_notice);

        addNoticeBtn = findViewById(R.id.add_notice_icon);

        addNoticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageNotice.this, UploadNotice.class));
            }
        });
    }
}