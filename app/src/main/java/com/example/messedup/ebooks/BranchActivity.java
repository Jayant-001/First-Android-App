package com.example.messedup.ebooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.messedup.R;

public class BranchActivity extends AppCompatActivity {

    TextView cse, it, ec, ic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);

        cse = findViewById(R.id.cse);
        it = findViewById(R.id.it);
        ec = findViewById(R.id.ec);
        ic = findViewById(R.id.ic);



        cse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BranchActivity.this, CSE.class);
                intent.putExtra("branch", "C S E");
                startActivity(intent);
            }
        });

        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BranchActivity.this, IT.class);
                intent.putExtra("branch", "I T");
                startActivity(intent);
            }
        });

        ec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BranchActivity.this, EC.class);
                intent.putExtra("branch", "E C");
                startActivity(intent);
            }
        });

        ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BranchActivity.this, IC.class);
                intent.putExtra("branch", "I C");
                startActivity(intent);
            }
        });
    }
}