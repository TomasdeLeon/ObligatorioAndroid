package com.example.obligatorioandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText txtMail, txtContraseña;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMail = findViewById(R.id.txtMail);
        txtContraseña = findViewById(R.id.txtContraseña);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void registrarse(View view) {
        String email = txtMail.getText().toString();
        String password = txtContraseña.getText().toString();

        //Verificamos que los campos de correo y contraseña no esten vacio
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Ingrese usuario y contraseña", Toast.LENGTH_SHORT).show();
        } else {
            //Registramos al usuario con el correo y pass ingresado
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //Si se registro al usuario
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Se ha registrado al usuario", Toast.LENGTH_SHORT).show();
                    }
                    //Sino se pudo registrar al usuario
                    else {
                        Toast.makeText(MainActivity.this, "Error al registrar al usuario", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void ingresar(View view) {
        String email = txtMail.getText().toString();
        String password = txtContraseña.getText().toString();

        //Verificamos que los campos de correo y contraseña no esten vacio
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Ingrese usuario y contraseña", Toast.LENGTH_SHORT).show();
        } else {
            //Nos logueamos con el correo y pass ingresado
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, BuscadorLibros.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Login fallido", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}