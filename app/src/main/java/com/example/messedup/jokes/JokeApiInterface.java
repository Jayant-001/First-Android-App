package com.example.messedup.jokes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JokeApiInterface {

    @GET("Any")
    Call<List<JokeData>> getPost();
}
