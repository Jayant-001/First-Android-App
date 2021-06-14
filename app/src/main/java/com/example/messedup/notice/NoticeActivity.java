package com.example.messedup.notice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.messedup.R;

public class NoticeActivity extends AppCompatActivity {

    private Button addNotice, showNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        addNotice = findViewById(R.id.add_notice);
        showNotice = findViewById(R.id.show_notice);


        addNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoticeActivity.this, AddNotice.class));
            }
        });

        showNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoticeActivity.this, ShowNotice.class));
            }
        });
    }
}