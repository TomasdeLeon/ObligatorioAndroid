package com.example.obligatorioandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VerLibro extends AppCompatActivity {

    private Libro libro;
    private Integer idLibro;
    private EditText txtTitulo;
    private EditText txtAutor;
    private EditText txtEditorial;
    private EditText txtPagina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_libro);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtAutor = findViewById(R.id.txtAutor);
        txtEditorial = findViewById(R.id.txtEditorial);
        txtPagina = findViewById(R.id.txtPagina);

        Bundle bundle =this.getIntent().getExtras();

        idLibro = bundle.getInt("ID");
        txtTitulo.setText(bundle.getString("Titulo"));
        txtAutor.setText(bundle.getString("Autor"));
        txtEditorial.setText(bundle.getString("Editorial"));
        txtPagina.setText(bundle.getInt("Pagina"));
    }

    /*public void modificar(View view) {
        DBLibro dbLibro = new DBLibro(getApplicationContext());

        if(libro != null){
            libro.setTxtLibroTitulo(txtTitulo.getText().toString());
            libro.setTxtLibroAutor(txtAutor.getText().toString());
            libro.setTxtLibroEditorial(txtEditorial.getText().toString());
            libro.setLibroPagina(Integer.parseInt(txtPagina.getText().toString()));
            dbLibro.update(libro);

            libro = null;
            txtTitulo.setText("");
            txtAutor.setText("");
            txtEditorial.setText("");
            txtPagina.setText("");
        }
    }*/

    public void editarLibro(View view) {
        DBLibro dbLibro = new DBLibro(getApplicationContext());
        libro = new Libro();

        libro.setTxtLibroTitulo(txtTitulo.getText().toString());
        libro.setTxtLibroAutor(txtAutor.getText().toString());
        libro.setTxtLibroEditorial(txtEditorial.getText().toString());
        libro.setLibroPagina(Integer.parseInt(txtPagina.getText().toString()));
        dbLibro.update(libro);

        finish();

    }

    public void eliminarLibro(View view) {
        DBLibro dbLibro = new DBLibro(getApplicationContext());

        dbLibro.delete(idLibro);
        finish();
    }

    public void regresar(View view) {
        finish();
    }
}