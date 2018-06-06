package com.tcv.peliculas.controller;


import android.content.Context;
import android.content.SharedPreferences;

import com.tcv.peliculas.R;

public class PeliculasViewModel {

    private Context context;

    public PeliculasViewModel(Context context) {
        this.context = context;
    }

    public String getUsername() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),
                Context.MODE_PRIVATE);
        return sharedPreferences.getString("usuario", "");
    }
}
