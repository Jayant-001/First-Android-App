package com.example.messedup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class BookActivity extends AppCompatActivity {

    TextView showYear, showBranch, showSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        showYear = findViewById(R.id.show_year);
        showBranch = findViewById(R.id.show_branch);
        showSubject = findViewById(R.id.show_subject);

        String y = getIntent().getStringExtra("year");
        String b = getIntent().getStringExtra("branch");
        String s = getIntent().getStringExtra("subject");

        showSubject.setText(s);
        showBranch.setText(b);
        showYear.setText(y);

    }
}