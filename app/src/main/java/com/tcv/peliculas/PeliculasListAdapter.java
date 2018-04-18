package com.tcv.peliculas;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

public class PeliculasListAdapter extends BaseAdapter{

    private List<Pelicula> peliculas;
    private Context context;

    public PeliculasListAdapter(List<Pelicula> peliculas, Context context) {
        this.peliculas = peliculas;
        this.context = context;
    }


    @Override
    public int getCount() {
        return peliculas.size();
    }

    @Override
    public Object getItem(int position) {
        return peliculas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return peliculas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.pelicula_item, parent, false);
        }
            //Obtengo la pelicula
            final Pelicula pelicula = peliculas.get(position);

            //Obtengo los elementos visuales
            TextView tituloTv = convertView.findViewById(R.id.titulo);
            TextView generoTv = convertView.findViewById(R.id.genero);
            ImageView imagenIv = convertView.findViewById(R.id.imagen);


            tituloTv.setText(pelicula.getTitulo());
            generoTv.setText(pelicula.getGenero());
            //TODO: Como resuelvo el tema de la imagen?
            Glide.with(context).load(pelicula.getImagen()).into(imagenIv);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PeliculaDetailsActivity.class);
                    intent.putExtra("pelicula", new Gson().toJson(pelicula));
                    context.startActivity(intent);
                }
            });

            return convertView;
    }
}
