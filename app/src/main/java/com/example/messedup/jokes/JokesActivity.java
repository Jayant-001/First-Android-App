package com.example.messedup.jokes;

import androidx.appcompat.app.AppCompatActivity;
import com.example.messedup.R;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.Button;
import android.widget.TextView;

public class JokesActivity extends AppCompatActivity {

    TextView textView;
    private Button shareJokeBtn, nextJokeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        shareJokeBtn = findViewById(R.id.share_joke_btn);
        nextJokeBtn = findViewById(R.id.next_joke_btn);


        textView = findViewById(R.id.joke_header);
        textView.setText("No Time To Laugh".toUpperCase());

        TextPaint paint = textView.getPaint();
        float width = paint.measureText("No Time To Laugh");

        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
//                        Color.parseColor("#F97C3C"),
//                        Color.parseColor("#FDB54E"),
//                        Color.parseColor("#64B678"),
//                        Color.parseColor("#478AEA"),
//                        Color.parseColor("#8446CC"),


                        Color.parseColor("#8446CC"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#F97C3C"),



                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);
    }
}