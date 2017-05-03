package com.example.toshibaceleron.tareadatabase02052017;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLControlador {

    private DB dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLControlador(Context c) {
        ourcontext = c;
    }

    public SQLControlador abrirBaseDeDatos() throws SQLException {
        dbhelper = new DB(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        dbhelper.close();
    }

    public void insertarDatos(String name) {
        ContentValues cv = new ContentValues();
        cv.put(DB.MIEMBRO_NOMBRE, name);
        database.insert(DB.TABLE_MEMBER, null, cv);
    }

    public Cursor leerDatos() {
        String[] todasLasColumnas = new String[] {
                DB.MIEMBRO_ID,
                DB.MIEMBRO_NOMBRE
        };
        Cursor c = database.query(DB.TABLE_MEMBER, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public int actualizarDatos(long memberID, String memberName) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DB.MIEMBRO_NOMBRE, memberName);
        int i = database.update(DB.TABLE_MEMBER, cvActualizar,
                DB.MIEMBRO_ID + " = " + memberID, null);
        return i;
    }

    public void deleteData(long memberID) {
        database.delete(DB.TABLE_MEMBER, DB.MIEMBRO_ID + "="
                + memberID, null);
    }
}