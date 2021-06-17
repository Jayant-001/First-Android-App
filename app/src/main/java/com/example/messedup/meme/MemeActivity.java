package com.example.messedup.meme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.messedup.R;
import com.example.messedup.jokes.JokesActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MemeActivity extends AppCompatActivity {

    ImageView memeImage;
    private Button memeShareBtn, memeNextBtn;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme);

        memeImage = findViewById(R.id.meme_image);
        memeShareBtn = findViewById(R.id.meme_share_btn);
        memeNextBtn = findViewById(R.id.meme_next_btn);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");

//        Picasso.get().load("https://i.redd.it/xfgm1kxiqr571.png").into(memeImage);

        loadMeme();
        dialog.show();

        memeNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadMeme();
            }
        });


    }

    void loadMeme() {

        dialog.show();
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://meme-api.herokuapp.com/gimme", null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String imageUrl = response.getString("url");
                            Log.d("jayant", imageUrl);
                            dialog.dismiss();

                            Picasso.get().load(imageUrl).into(memeImage);

                        } catch (JSONException e) {
                            dialog.dismiss();
                            Log.d("jayant", e.toString());
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(MemeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("jayant", error.toString());
            }
        });


        requestQueue.add(request);

    }
}