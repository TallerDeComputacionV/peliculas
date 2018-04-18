package com.tcv.peliculas;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

public class PeliculaDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle args = getIntent().getExtras();
        Pelicula pelicula = new Gson().fromJson(args.getString("pelicula"), Pelicula.class);

        TextView tituloTv = findViewById(R.id.titulo);
        TextView generoTv = findViewById(R.id.genero);
        ImageView imagen = findViewById(R.id.imagen);
        TextView lanzamientoTV = findViewById(R.id.lanzamiento);
        TextView duracionTv = findViewById(R.id.duracion);
        TextView puntuacionTv = findViewById(R.id.puntuacion);
        TextView artistasTv = findViewById(R.id.artistas_principales);

        tituloTv.setText(pelicula.getTitulo());
        generoTv.setText(pelicula.getGenero());
        //TODO: imagen
        Glide.with(this).load(pelicula.getImagen()).into(imagen);
        lanzamientoTV.setText(pelicula.getLanzamiento());
        duracionTv.setText(String.valueOf(pelicula.getDuracion()));
        puntuacionTv.setText(String.valueOf(pelicula.getPuntuacion()));
        artistasTv.setText(pelicula.getArtistas().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorite) {
            item.setIcon(R.drawable.ic_star_black);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
