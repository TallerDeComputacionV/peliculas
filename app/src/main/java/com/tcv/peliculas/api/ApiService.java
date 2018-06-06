package com.tcv.peliculas.api;



import com.tcv.peliculas.model.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {

    @GET("user/login")
    public Call<LoginResponse> login(@Query("username") final String id,
                                     @Query("pwd") final String password);

    @GET("peliculas")
    public Call<List<Pelicula>> getPeliculas();
}