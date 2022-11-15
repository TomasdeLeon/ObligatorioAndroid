package com.example.obligatorioandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BuscadorLibros extends AppCompatActivity {

    private EditText txtConsulta;
    private Spinner spinnerTipo;
    private TextView txtLibroTitulo;
    private TextView txtLibroAutor;
    private TextView txtLibroEditorial;
    private TextView txtResultado;

    private List<Libro> libros = new ArrayList<>();

    ConnectivityManager conexion;
    NetworkInfo infoRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador_libros);

        txtConsulta = findViewById(R.id.txtConsulta);
        spinnerTipo = findViewById(R.id.spinnerTipo);
        txtLibroTitulo = findViewById(R.id.txtLibroTitulo);
        txtLibroAutor = findViewById(R.id.txtLibroAutor);
        txtLibroEditorial = findViewById(R.id.txtLibroEditorial);
        txtResultado = findViewById(R.id.txtResultado);

        spinnerTipo = findViewById(R.id.spinnerTipo);
        String[] tipo = {"books", "Revista"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tipo);
        spinnerTipo.setAdapter(adapter);

        conexion = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        infoRed = conexion.getActiveNetworkInfo();

    }

    public void buscar(View view) {
        if(infoRed != null && infoRed.isConnected()) {
            String consulta = txtConsulta.getText().toString();
            new BuscadorTask(txtLibroTitulo, txtLibroAutor, txtLibroEditorial).execute(consulta);
        }
        else {
            Toast.makeText(this,"Sin conexion a la red",Toast.LENGTH_SHORT).show();
        }
    }

    public void agregar(View view) {
        String tituloLibro = txtLibroTitulo.getText().toString();
        String autorLibro = txtLibroAutor.getText().toString();
        String editorialLibro = txtLibroEditorial.getText().toString();

        Libro libro = new Libro();
        libro.setTxtLibroTitulo(tituloLibro);
        libro.setTxtLibroAutor(autorLibro);
        libro.setTxtLibroEditorial(editorialLibro);

        DBLibro dbLibro = new DBLibro(getApplicationContext());
        long valorResultado = dbLibro.insert(libro);
        String mensaje;

        txtLibroTitulo.setText("");
        txtLibroEditorial.setText("");
        txtLibroAutor.setText("");

        if (valorResultado > 0) {
            mensaje = "Se agregó con éxito el libro: " + valorResultado;
        } else {
            mensaje = "No se pudo agregar el libro solicitado.";
        }

        txtResultado.setText(mensaje);

    }
}