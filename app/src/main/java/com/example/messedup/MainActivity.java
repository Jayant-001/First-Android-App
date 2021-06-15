package com.example.messedup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.messedup.dashboard.HomeActivity;
import com.example.messedup.dashboard.NewActivity;
import com.example.messedup.notice.NoticeActivity;

public class MainActivity extends AppCompatActivity {

    private CardView first, second, third, fourth, fifth, sixth;
    private TextView notice, designPage;
    TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        designPage = findViewById(R.id.design_page);

        designPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewActivity.class));
            }
        });

        text2 = findViewById(R.id.text2);
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                Toast.makeText(MainActivity.this, "kldsjf", Toast.LENGTH_SHORT).show();
            }
        });

        notice = findViewById(R.id.notice_page);
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NoticeActivity.class));
            }
        });

        first = findViewById(R.id.first_year);
        second = findViewById(R.id.second_year);
        third = findViewById(R.id.third_year);
        fourth = findViewById(R.id.forth_year);
        fifth = findViewById(R.id.fifth_year);
        sixth = findViewById(R.id.sixth_year);

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BranchActivity.class);
                intent.putExtra("year", "first_year");
                startActivity(intent);
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, BranchActivity.class);
                        intent.putExtra("year", "second_year");
                        startActivity(intent);
                    }
                });

        third.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, BranchActivity.class);
                        intent.putExtra("year", "third_year");
                        startActivity(intent);
                    }
                });

        fourth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, BranchActivity.class);
                        intent.putExtra("year", "forth_year");
                        startActivity(intent);
                    }
                });

        fifth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, BranchActivity.class);
                        intent.putExtra("year", "fifth_year");
                        startActivity(intent);
                    }
                });

        sixth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, BranchActivity.class);
                        intent.putExtra("year", "sixth_year");
                        startActivity(intent);
                    }
                });



    }
}