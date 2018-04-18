package com.tcv.peliculas;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("peliculas")
    public Call<List<Pelicula>> getPeliculas();
}
