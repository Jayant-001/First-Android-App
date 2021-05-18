package com.example.messedup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.messedup.Model.TestActivity;

public class MainActivity extends AppCompatActivity {

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button is working", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

    }
}