package com.example.obligatorioandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;

public class DBConexion {
    private static final String TAG = DBConexion.class.getSimpleName().toString();

    private static final String DATABASE_NAME = "ObligatorioAndroid.db";

    protected Context mContext;
    protected static DatabaseHelper mDbHelper;

    public DBConexion(Context context) {
        mContext = context;
    }

    private DatabaseHelper openDb() {
        if (mDbHelper == null) {
            mDbHelper = new DatabaseHelper(mContext);
        }
        return mDbHelper;
    }

    public SQLiteDatabase openWrite() {
        return openDb().getWritableDatabase();
    }

    public SQLiteDatabase openRead() {
        return openDb().getReadableDatabase();
    }

    public void closeDb() {
        if (mDbHelper != null)
            mDbHelper.close();
    }

    protected static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DBLibro.CREATE_TABLA);
            } catch (Exception e) {
                Log.e(TAG, "ERROR AL CREAR LAS TABLAS " + e.getMessage());
                System.exit(0);
            }
        }

        @Override
        public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + Libro.TABLA);
            onCreate(db); // Creo las tablas nuevamente
        }
    }
}
