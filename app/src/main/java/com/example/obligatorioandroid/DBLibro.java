package com.example.obligatorioandroid;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class DBLibro extends DBConexion{

    public DBLibro(Context context){
        super(context);
    }

    static final String CREATE_TABLA = "CREATE TABLE " + Libro.TABLA + "("
            + Libro.Libro_Id + " INTEGER PRIMARY KEY, "
            + Libro.Libro_Usuario + " TEXT, "
            + Libro.Libro_Titulo + " TEXT, "
            + Libro.Libro_Autor + " TEXT, "
            + Libro.Libro_Editorial + " TEXT, "
            + Libro.Libro_Descripcion + " TEXT "
            + Libro.Libro_Pagina + " TEXT);";


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

    @SuppressLint("Range")
    private Libro cursorToObject(Cursor cursor){
        Libro libro = new Libro();

        libro.setLibroId(cursor.getInt(cursor.getColumnIndex(Libro.Libro_Id)));
        libro.setTxtLibroTitulo(cursor.getString(cursor.getColumnIndex(Libro.Libro_Titulo)));
        libro.setTxtLibroAutor(cursor.getString(cursor.getColumnIndex(Libro.Libro_Autor)));

        return libro;
    }


    public List<Libro> getAll(String mail){
        List<Libro> lista = new ArrayList<Libro>();
        SQLiteDatabase db = openRead();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Libro.TABLA +
                " WHERE " + Libro.Libro_Usuario + " = " + mail, null);

        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    Libro libro = cursorToObject(cursor);
                    lista.add(libro);
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
        closeDb();

        return lista;
    }
}
