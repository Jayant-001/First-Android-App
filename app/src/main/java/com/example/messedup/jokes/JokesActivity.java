package com.example.messedup.jokes;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.messedup.R;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JokesActivity extends AppCompatActivity {

    TextView textView, jokeSetup, jokeDelivery;
    private Button shareJokeBtn, nextJokeBtn;

    private JokeApiInterface apiInterface;
    private List<JokeData> list;

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




        jokeSetup = findViewById(R.id.joke_setup);
        jokeDelivery = findViewById(R.id.joke_delivery);

        loadJoke();

        nextJokeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadJoke();
            }
        });


//
//        list = new ArrayList<>();
//        apiInterface = RetrofitInstance.getRetrofit().create(JokeApiInterface.class);
//
//        apiInterface.getPost().enqueue(new Callback<List<JokeData>>() {
//            @Override
//            public void onResponse(Call<List<JokeData>> call, Response<List<JokeData>> response) {
//
//                if(response.body().size() > 0) {
//                    list.addAll(response.body());
//
//                    for(int i = 0; i < list.size(); i++) {
//                        String joke = list.get(i).getSetup();
//                        jokeSetup.setText(joke);
//                    }
//
//                    Toast.makeText(JokesActivity.this, "List is not empty", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(JokesActivity.this, "List is empty", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<JokeData>> call, Throwable t) {
//
//                Toast.makeText(JokesActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d("jayant", t.toString());
//            }
//        });

    }

    // api site link - https://sv443.net/jokeapi/v2/

    void loadJoke() {

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://v2.jokeapi.dev/joke/Any?blacklistFlags=sexist,explicit&type=twopart", null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String setup = response.getString("setup");
                            String delivery = response.getString("delivery");

                            jokeSetup.setText(setup);
                            jokeDelivery.setText(delivery);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JokesActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("jayant", error.toString());
            }
        });

        requestQueue.add(request);
    }
}