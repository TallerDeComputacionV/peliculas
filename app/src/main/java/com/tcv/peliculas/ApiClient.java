package com.tcv.peliculas;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiInterface client;

    public static ApiInterface getClient() {
        if(client == null) {
            client = new Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/TallerDeComputacionV/peliculas/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiInterface.class);
        }
        return client;
    }
}
