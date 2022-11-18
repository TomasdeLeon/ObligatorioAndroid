package com.example.obligatorioandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBLibro extends DBConexion{

    public DBLibro(Context context){
        super(context);
    }

    static final String CREATE_TABLA = "CREATE TABLE " + Libro.TABLA + "("
            + Libro.Libro_Id + " INTEGER PRIMARY KEY, "
            + Libro.Libro_Titulo + " TEXT, "
            + Libro.Libro_Autor + " TEXT, "
            + Libro.Libro_Editorial + " TEXT, "
            + Libro.Libro_Descripcion + " TEXT);";


    public long insert(Libro libro){
        ContentValues values = new ContentValues();

        values.put(Libro.Libro_Id, libro.getLibroId());
        values.put(Libro.Libro_Titulo, libro.getTxtLibroTitulo());
        values.put(Libro.Libro_Autor, libro.getTxtLibroAutor());
        values.put(Libro.Libro_Editorial, libro.getTxtLibroEditorial());
        values.put(Libro.Libro_Descripcion, libro.getTxtLibroDescripcion());

        SQLiteDatabase db = openWrite();
        long res = db.insert(Libro.TABLA, null, values);
        db.close();

        return res;
    }

    public long delete(int idPersona){

        SQLiteDatabase db = openWrite();
        long res = db.delete(Libro.TABLA, Libro.Libro_Id + " = ?", new String[] {Integer.toString(idPersona) } );
        db.close();

        return res;

    }
}
