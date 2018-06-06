package com.tcv.peliculas.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tcv.peliculas.R;
import com.tcv.peliculas.api.ApiClient;
import com.tcv.peliculas.controller.PeliculasListAdapter;
import com.tcv.peliculas.controller.PeliculasViewModel;
import com.tcv.peliculas.model.Pelicula;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaPeliculasActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView peliculasRv;
    private PeliculasListAdapter peliculasAdapter;
    private List<Pelicula> peliculas;
    private PeliculasViewModel peliculasViewModel = new PeliculasViewModel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        peliculas = new ArrayList<>();
        peliculasRv = (RecyclerView) findViewById(R.id.peliculas_rv);
        peliculasAdapter = new PeliculasListAdapter(peliculas, this);
        peliculasRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        peliculasRv.setAdapter(peliculasAdapter);
        ApiClient.getClient(this).getPeliculas().enqueue(new Callback<List<Pelicula>>() {
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //Usar esto para cerrar sesion desde aca
        if (id == R.id.action_settings) {
            cerrarSesion();
            return true;
        }
        if (id == R.id.action_contactarse) {
            enviarEmail();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void enviarEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","contacto@peliculas.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contacto de " + peliculasViewModel.getUsername());
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
    }

    private void cerrarSesion() {
        SharedPreferences sharedPreferences =
            getSharedPreferences(getString(R.string.app_name),
                    Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
