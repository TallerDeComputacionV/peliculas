package com.tcv.peliculas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences userDetails = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);

        //Cuando se entre a la app chequear por getString
        //Si ya existe el usuario y la password entrar directo
        //Sino pedirle que se loguee con registrar, y cuando lo haga, persistir las credenciales
        userDetails.edit().putString("usuario", "jose").putString("password", "1234").commit();
    }

    //TODO: Hacer el login
}
