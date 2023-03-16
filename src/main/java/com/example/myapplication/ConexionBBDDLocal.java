package com.example.myapplication;

import static com.example.myapplication.ComandosSQL.CREAR_PRENDA;
import static com.example.myapplication.ComandosSQL.DESCRIP;
import static com.example.myapplication.ComandosSQL.FECHA;
import static com.example.myapplication.ComandosSQL.TITULO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.Entidades.Prenda;

import java.util.ArrayList;
import java.util.List;

public class ConexionBBDDLocal extends SQLiteOpenHelper {
    public ConexionBBDDLocal(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // cuando se crea una instancia de la clase se ejecuta el método de CREAR_PRENDA que crea una BD
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREAR_PRENDA);
    }

    // cuando se realiza algun cambio en la BD se elimina la creada anteriormente para dar pie a crear otra
    //para que no de ningun error de no haber ninguna BD creada
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //na que hacer
    }

    public long anadir_prenda(String titulo, String descr, String fecha) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ComandosSQL.TITULO, titulo);
        values.put(ComandosSQL.DESCRIP, descr);
        values.put(ComandosSQL.FECHA, fecha);
        //values.put(ComandosSQL.FOTO, foto);

        long rowId = db.insert("prendas", null, values);

        db.close();

        return rowId;
    }


}
