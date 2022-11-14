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

        spinnerTipo = findViewById(R.id.spinnerTipo);
        String[] tipo = {"Libro", "Revista"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tipo);
        spinnerTipo.setAdapter(adapter);

        conexion = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        infoRed = conexion.getActiveNetworkInfo();

    }

    public void buscar(View view) {
        if(infoRed != null && infoRed.isConnected()){

            spinnerTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    String selec= spinnerTipo.getSelectedItem().toString();
                    if (selec.equals("Libro")) {
                        String consulta = txtConsulta.getText().toString();
                        new BuscadorTask(txtLibroTitulo, txtLibroAutor, txtLibroEditorial).execute(consulta);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    //no hacemos nada
                }
            });
        }
        else {
            Toast.makeText(this,"Sin conexion a la red",Toast.LENGTH_SHORT).show();
        }
    }
}