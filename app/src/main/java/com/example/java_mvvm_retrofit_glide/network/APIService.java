package com.example.java_mvvm_retrofit_glide.network;

import com.example.java_mvvm_retrofit_glide.model.MovieModel;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface APIService {

    @GET("volley_array.json")
    Call<List<MovieModel>> getMovieList();

}
