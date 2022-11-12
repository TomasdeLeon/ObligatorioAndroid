package com.example.obligatorioandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class BuscadorLibros extends AppCompatActivity {

    private EditText txtConsulta;
    private Spinner spinnerTipo;
    private TextView txtLibroTitulo;
    private TextView txtLibroAutor;
    private TextView txtLibroEditorial;

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

        conexion = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        infoRed = conexion.getActiveNetworkInfo();

    }

    public void buscar(View view) {
        String selec= spinnerTipo.getSelectedItem().toString();
            if(selec.equals("Libro")){
                    String consulta = txtConsulta.getText().toString();
                    new BuscadorTask(txtLibroTitulo, txtLibroAutor, txtLibroEditorial).execute(consulta);
                }
            else
                if(selec.equals("Revista")){
                    if(infoRed != null && infoRed.isConnected()) {
                        String consulta = txtConsulta.getText().toString();
                        new BuscadorTask(txtLibroTitulo, txtLibroAutor, txtLibroEditorial).execute(consulta);
                    }
            }
        else {
            Toast.makeText(this,"Sin conexion a la red",Toast.LENGTH_SHORT).show();
        }
    }
}