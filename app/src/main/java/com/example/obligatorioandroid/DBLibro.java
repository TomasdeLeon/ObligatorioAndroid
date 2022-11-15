package com.example.obligatorioandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBLibro extends DBConexion{

    public DBLibro(Context context){
        super(context);
    }

    static final String CREATE_TABLA = "CREATE TABLE " + Libro.TABLA + "("
            + Libro.Libro_Titulo + " TEXT PRIMARY KEY, "
            + Libro.Libro_Autor + " TEXT, "
            + Libro.Libro_Editorial + " TEXT);";


    public long insert(Libro libro){
        ContentValues values = new ContentValues();

        values.put(Libro.Libro_Titulo, libro.getTxtLibroTitulo());
        values.put(Libro.Libro_Autor, libro.getTxtLibroAutor());
        values.put(Libro.Libro_Editorial, libro.getTxtLibroEditorial());

        SQLiteDatabase db = openWrite();
        long res = db.insert(Libro.TABLA, null, values);
        db.close();

        return res;
    }
}
