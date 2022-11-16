package com.example.obligatorioandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    public void buscarLibro(View view) {
        Intent i = new Intent(MenuPrincipal.this, BuscadorLibros.class);
        startActivity(i);
    }


    public void misLibros(View view) {
        Intent i = new Intent(MenuPrincipal.this, GrillaLibros.class);
        startActivity(i);
    }

    public void salir(View view) {
        finish();
    }
}