package com.example.obligatorioandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuPrincipal extends AppCompatActivity {

    private String mail = "";
    TextView txtMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mail = user.getEmail();
    }

    public void buscarLibro(View view) {
        Intent i = new Intent(MenuPrincipal.this, BuscadorLibros.class);
        startActivity(i);
    }


    public void misLibros(View view) {
        Intent i = new Intent(MenuPrincipal.this, GrillaLibros.class);
        i.putExtra("mail", mail);
        startActivity(i);

    }

    public void salir(View view) {
        finish();
    }
}