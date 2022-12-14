package com.example.obligatorioandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GrillaLibros extends AppCompatActivity {

    private List<Libro> libros = new ArrayList<Libro>();
    private LibroRecyclerViewAdapter adapter;
    private String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grilla_libros);
        mail = getIntent().getStringExtra("mail");

        RecyclerView recyclerView = findViewById(R.id.recyclerLibros);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new LibroRecyclerViewAdapter(libros);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        recargarLibrosDesdeDB();
        adapter.notifyDataSetChanged();
    }

    private void recargarLibrosDesdeDB() {
        DBLibro db = new DBLibro(getApplicationContext());
        libros.clear();
        libros.addAll(db.getAll(mail));
    }

    public void regresar(View view) {
        finish();
    }
}