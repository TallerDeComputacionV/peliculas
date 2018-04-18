package com.tcv.peliculas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaPeliculasActivity extends AppCompatActivity {

    private ListView peliculasLv;
    private PeliculasListAdapter peliculasAdapter;
    private List<Pelicula> peliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peliculas = new ArrayList<>();
        peliculasLv = findViewById(R.id.peliculas_lv);
        peliculasAdapter = new PeliculasListAdapter(peliculas, this);
        peliculasLv.setAdapter(peliculasAdapter);
        ApiClient.getClient().getPeliculas().enqueue(new Callback<List<Pelicula>>() {
            @Override
            public void onResponse(Call<List<Pelicula>> call, Response<List<Pelicula>> response) {
                peliculas.clear();
                List<Pelicula> peliculasResponse = response.body();
                peliculas.addAll(peliculasResponse);
                peliculasAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Pelicula>> call, Throwable throwable) {
                Toast.makeText(ListaPeliculasActivity.this, "Ocurrio un error al querer obtener la lista de peliculas.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
