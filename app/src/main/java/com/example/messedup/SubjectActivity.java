package com.example.messedup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SubjectActivity extends AppCompatActivity {

    TextView sb1, sb2, sb3, sb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        sb1 = findViewById(R.id.sub1);
        sb2 = findViewById(R.id.sub2);
        sb3 = findViewById(R.id.sub3);
        sb4 = findViewById(R.id.sub4);

        String year = getIntent().getStringExtra("year");
        String branch = getIntent().getStringExtra("branch");


        String sub1, sub2, sub3, sub4;
        switch (year) {
            case "first_year":
                sub1 = "math";
                sub2 = "physics";
                sub3 = "chemistry";
                sub4 = "FCIT";
                break;
            case "second_year":
                sub1 = "math2";
                sub2 = "physics2";
                sub3 = "chemistry2";
                sub4 = "OAT";
                break;
            case "third_year":
                sub1 = "math3";
                sub2 = "physics3";
                sub3 = "c programming";
                sub4 = "Graphics designing";
                break;
            case "forth_year":
                sub1 = "java";
                sub2 = "D E";
                sub3 = "WEb tech";
                sub4 = "Databases";
                break;
            case "fifth_year":
                sub1 = "Python";
                sub2 = "PHP";
                sub3 = "Software engineering";
                sub4 = "I O T";
                break;
            case "sixth_year":
                sub1 = "Android";
                sub2 = "Cloud computing";
                sub3 = "Advance java";
                sub4 = "I M E D";
                break;
            default:
                sub1 = "NULL";
                sub2 = "NULL";
                sub3 = "NULL";
                sub4 = "NULL";
                break;
        }

        Toast.makeText(this, "" + year, Toast.LENGTH_SHORT).show();

        Log.d("subject", sub1);
        Log.d("subject", sub2);
        Log.d("subject", sub3);
        Log.d("subject", sub4);

        sb1.setText(sub1);
        sb2.setText(sub2);
        sb3.setText(sub3);
        sb4.setText(sub4);

        sb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubjectActivity.this, BookActivity.class);
                intent.putExtra("year", year);
                intent.putExtra("branch", getIntent().getStringExtra("branch"));
                intent.putExtra("subject", sb1.getText().toString());
                startActivity(intent);
            }
        });

        sb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubjectActivity.this, BookActivity.class);
                intent.putExtra("year", year);
                intent.putExtra("branch", getIntent().getStringExtra("branch"));
                intent.putExtra("subject", sb2.getText().toString());
                startActivity(intent);
            }
        });

        sb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubjectActivity.this, BookActivity.class);
                intent.putExtra("year", year);
                intent.putExtra("branch", getIntent().getStringExtra("branch"));
                intent.putExtra("subject", sb3.getText().toString());
                startActivity(intent);
            }
        });

        sb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubjectActivity.this, BookActivity.class);
                intent.putExtra("year", year);
                intent.putExtra("branch", getIntent().getStringExtra("branch"));
                intent.putExtra("subject", sb4.getText().toString());
                startActivity(intent);
            }
        });
    }
}