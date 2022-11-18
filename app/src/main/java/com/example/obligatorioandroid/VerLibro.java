package com.example.obligatorioandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class VerLibro extends AppCompatActivity {

    private Libro libro;
    private TextView idLibro;
    private TextView txtTitulo;
    private TextView txtAutor;
    private TextView txtEditorial;
    private TextView txtDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_libro);

        idLibro = findViewById(R.id.txtId);
        txtTitulo = findViewById(R.id.txtTitulo);
        txtAutor = findViewById(R.id.txtAutor);
        txtEditorial = findViewById(R.id.txtEditorial);
        txtDescripcion = findViewById(R.id.txtDescripcion);

        Bundle bundle =this.getIntent().getExtras();

        idLibro.setText(bundle.getString("ID"));
        txtTitulo.setText(bundle.getString("Titulo"));
        txtAutor.setText(bundle.getString("Autor"));
        txtEditorial.setText(bundle.getString("Editorial"));
        txtDescripcion.setText(bundle.getString("Descripcion"));
    }

    public void eliminarLibro(View view) {
        DBLibro dbLibro = new DBLibro(getApplicationContext());

        dbLibro.delete(Integer.parseInt(idLibro.getText().toString()));
        finish();
    }

    public void regresar(View view) {
        finish();
    }
}