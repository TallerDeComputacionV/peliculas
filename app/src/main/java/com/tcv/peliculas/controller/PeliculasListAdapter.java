package com.tcv.peliculas.controller;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tcv.peliculas.R;
import com.tcv.peliculas.model.Pelicula;
import com.tcv.peliculas.view.PeliculaDetailsActivity;

import java.util.List;

public class PeliculasListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Pelicula> peliculas;
    private Context context;

    public PeliculasListAdapter(List<Pelicula> peliculas, Context context) {
        this.peliculas = peliculas;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PeliculaViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.pelicula_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((PeliculaViewHolder) viewHolder).bind(peliculas.get(position));
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    private class PeliculaViewHolder extends RecyclerView.ViewHolder {
        private final TextView tituloTv;
        private final TextView generoTv;
        private final ImageView imagenIv;
        public PeliculaViewHolder(View itemView) {
            super(itemView);
            tituloTv = itemView.findViewById(R.id.titulo);
            generoTv = itemView.findViewById(R.id.genero);
            imagenIv = itemView.findViewById(R.id.imagen);
        }

        public void bind(final Pelicula pelicula) {
            tituloTv.setText(pelicula.getTitulo());
            generoTv.setText(pelicula.getGenero());
            imagenIv.setImageResource(pelicula.getImagen());
            //Glide.with(context).load(pelicula.getImagen()).into(imagenIv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PeliculaDetailsActivity.class);
                    intent.putExtra("pelicula", new Gson().toJson(pelicula));
                    context.startActivity(intent);
                }
            });
        }
    }
}
