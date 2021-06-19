package com.example.messedup.meme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.messedup.R;
import com.example.messedup.jokes.JokesActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import static android.view.View.GONE;

public class MemeActivity extends AppCompatActivity {

    ImageView memeImage;
    private Button memeShareBtn, memeNextBtn;

    private ProgressDialog dialog;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme);

        memeImage = findViewById(R.id.meme_image);
        memeShareBtn = findViewById(R.id.meme_share_btn);
        memeNextBtn = findViewById(R.id.meme_next_btn);

        dialog = new ProgressDialog(this);
        progressBar = findViewById(R.id.progressBar);
        dialog.setMessage("Loading...");

//        Picasso.get().load("https://i.redd.it/xfgm1kxiqr571.png").into(memeImage);

        loadMeme();

        memeNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadMeme();
            }
        });


    }


    // meme api link - https://github.com/D3vd/Meme_Api
    void loadMeme() {

        progressBar.setVisibility(View.VISIBLE);
//        dialog.show();
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://meme-api.herokuapp.com/gimme", null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String imageUrl = response.getString("url");

                            progressBar.setVisibility(GONE);
                            dialog.dismiss();
//                            Picasso.get().load(imageUrl).into(memeImage);


                            Glide.with(MemeActivity.this).load(imageUrl).listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable  GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    progressBar.setVisibility(GONE);
                                    return false;
                                }
                            }).into(memeImage);


                        } catch (JSONException e) {
                            progressBar.setVisibility(GONE);
                            dialog.dismiss();
                            Log.d("jayant", e.toString());
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(GONE);
                dialog.dismiss();
                Toast.makeText(MemeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("jayant", error.toString());
            }
        });


        requestQueue.add(request);

    }
}