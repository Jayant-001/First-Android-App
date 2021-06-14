package com.example.messedup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BranchActivity extends AppCompatActivity {

    TextView cse, ee, ec, ic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);

        cse = findViewById(R.id.cse);
        ee = findViewById(R.id.ee);
        ec = findViewById(R.id.ec);
        ic = findViewById(R.id.ic);

        String year_text = getIntent().getStringExtra("year");


        cse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BranchActivity.this, SubjectActivity.class);
                intent.putExtra("year", year_text);
                intent.putExtra("branch", "C S E");
                startActivity(intent);
            }
        });

        ee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BranchActivity.this, SubjectActivity.class);
                intent.putExtra("year", year_text);
                intent.putExtra("branch", "E E");
                startActivity(intent);
            }
        });

        ec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BranchActivity.this, SubjectActivity.class);
                intent.putExtra("year", year_text);
                intent.putExtra("branch", "E C");
                startActivity(intent);
            }
        });

        ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BranchActivity.this, SubjectActivity.class);
                intent.putExtra("year", year_text);
                intent.putExtra("branch", "I C");
                startActivity(intent);
            }
        });
    }
}