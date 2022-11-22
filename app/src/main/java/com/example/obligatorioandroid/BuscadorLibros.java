package com.example.obligatorioandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class BuscadorLibros extends AppCompatActivity {

    private String mail = "";

    private EditText tituloDB;
    private EditText autorDB;
    private EditText editorialDB;
    private EditText txtConsulta;
    private Spinner spinnerTipo;
    private EditText txtLibroTitulo;
    private EditText txtLibroAutor;
    private EditText txtLibroEditorial;
    private EditText txtLibroPagina;

    //private List<Libro> libros = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador_libros);

        txtConsulta = findViewById(R.id.txtConsulta);
        spinnerTipo = findViewById(R.id.spinnerTipo);
        txtLibroTitulo = findViewById(R.id.txtLibroTitulo);
        txtLibroAutor = findViewById(R.id.txtLibroAutor);
        txtLibroEditorial = findViewById(R.id.txtLibroEditorial);

        tituloDB = findViewById(R.id.txtLibroTitulo);
        autorDB = findViewById(R.id.txtLibroAutor);
        editorialDB = findViewById(R.id.txtLibroEditorial);

        spinnerTipo = findViewById(R.id.spinnerTipo);
        String[] tipo = {"books", "magazines"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tipo);
        spinnerTipo.setAdapter(adapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            mail = user.getEmail();
        }

    }

    public void buscar(View view) {
        ConnectivityManager conexion = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo infoRed = conexion.getActiveNetworkInfo();

        if(infoRed != null && infoRed.isConnected()) {
            String tipoDocumento = spinnerTipo.getSelectedItem().toString();

            String consulta = txtConsulta.getText().toString();
            new BuscadorTask(txtLibroTitulo, txtLibroAutor, txtLibroEditorial).execute(consulta, tipoDocumento);
        }
        else {
            Toast.makeText(this,"Sin conexion a la red",Toast.LENGTH_SHORT).show();
        }
    }



    public void agregar(View view) {
        String tituloLibro = tituloDB.getText().toString();
        String autorLibro = autorDB.getText().toString();
        String editorialLibro = editorialDB.getText().toString();
        String mailUsuario = mail;
        Integer pagina = 0;
        Libro libro = new Libro();

        if(TextUtils.isEmpty(tituloLibro) || TextUtils.isEmpty(autorLibro) || TextUtils.isEmpty(editorialLibro) || TextUtils.isEmpty(pagina.toString())){
            Toast.makeText(this, "Error al cargar", Toast.LENGTH_SHORT).show();
        } else{
            libro.setLibroUsuario(mailUsuario);
            libro.setTxtLibroTitulo(tituloLibro);
            libro.setTxtLibroAutor(autorLibro);
            libro.setTxtLibroEditorial(editorialLibro);
            libro.setLibroPagina(pagina);

            DBLibro dbLibro = new DBLibro(getApplicationContext());
            long valorResultado = dbLibro.insert(libro);

            //txtLibroTitulo.setText("");
            //txtLibroAutor.setText("");
            //txtLibroEditorial.setText("");

            if (valorResultado > 0) {
                Toast.makeText(this, "Libro agregado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No se pudo agregar el libro solicitado", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void volver(View view) {
        finish();
    }
}