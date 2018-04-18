package com.tcv.peliculas;

import android.net.Uri;

public class Pelicula {
    private String titulo;
    private String genero;
    private Uri imagenUrl;


    public Pelicula(String titulo, String genero, Uri imagenUrl) {
        this.titulo = titulo;
        this.genero = genero;
        this.imagenUrl = imagenUrl;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public Uri getImagenUrl() {
        return imagenUrl;
    }
}
