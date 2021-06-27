package com.example.messedup.ebooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.messedup.R;

public class SemesterActivity extends AppCompatActivity {

    private TextView firstSem, secondSen, thirdSem, fourthSem, fifthSem, sixthSem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);

        firstSem = findViewById(R.id.first_sem);
        secondSen = findViewById(R.id.second_sem);
        thirdSem = findViewById(R.id.third_sem);
        fourthSem = findViewById(R.id.fourth_sem);
        fifthSem = findViewById(R.id.fifth_sem);
        sixthSem = findViewById(R.id.sixth_sem);

        String branch = getIntent().getStringExtra("branch");


        firstSem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubjectActivity.class);
                intent.putExtra("branch", branch);
                intent.putExtra("sem", "first_sem");
                startActivity(intent);
            }
        });



    }
}