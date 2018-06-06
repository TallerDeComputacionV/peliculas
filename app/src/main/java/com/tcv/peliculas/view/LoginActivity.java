package com.tcv.peliculas.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tcv.peliculas.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
            if(credencialesExisten()) {
                Intent intent =
                        new Intent(LoginActivity.this,
                        ListaPeliculasActivity.class);
                LoginActivity.this.startActivity(intent);
                LoginActivity.this.finish();
            } else {
                inicializarVista();
            }
        }

    private boolean credencialesExisten() {
        SharedPreferences sharedPreferences =
        LoginActivity.this.getSharedPreferences(
                getString(R.string.app_name),Context.MODE_PRIVATE);
        return sharedPreferences.contains("usuario");
    }

    private void inicializarVista() {
        final EditText usuarioEt = (EditText) findViewById(R.id.usuario_et);
        final EditText contraseñaEt = (EditText) findViewById(R.id.contraseña_et);
        Button ingresarBtn = (Button) findViewById(R.id.ingresar_btn);

        ingresarBtn.setOnClickListener(new View.OnClickListener() {

            private int intentos = 0;

            @Override
            public void onClick(View view) {
                String usuario = usuarioEt.getText().toString();
                String contraseña = contraseñaEt.getText().toString();

                if (usuario.equals("alan") && contraseña.equals("1234")) {
                    persistirCredenciales("alan","1234");
                    Intent intent = new Intent(LoginActivity.this,
                            ListaPeliculasActivity.class);
                    LoginActivity.this.startActivity(intent);
                    LoginActivity.this.finish();
                } else {
                    intentos++;
                    if(intentos == 3) {
                        Toast.makeText(LoginActivity.this,
                                "Hubo mas de 3 intentos fallidos, cerrando aplicacion.",
                                Toast.LENGTH_LONG).show();
                        LoginActivity.this.finish();
                    } else {
                        Toast.makeText(LoginActivity.this,
                                "El usuario o contrasenia ingresados es incorrecto...",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void persistirCredenciales(String usuario, String contraseña)
    {
        SharedPreferences sharedPreferences =
                LoginActivity.this.getSharedPreferences(getString(R.string.app_name),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("usuario",usuario);
        editor.commit();
    }
}
