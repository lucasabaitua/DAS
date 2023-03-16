package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Identificacion extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_sesion);
        Button iniciarSesion = findViewById(R.id.botonLogin);
        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText user = findViewById(R.id.usuarioEditText);
                EditText pass = findViewById(R.id.contraEditText);
                comprobarIniSes(user.getText().toString(), pass.getText().toString());
            }
        });

        Button registrarse = findViewById(R.id.botonRegistro);
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Identificacion.this, RegistroUsuarios.class);
                startActivity(intent);
            }
        });
    }

    public void comprobarIniSes(String usuario, String contra){
        ConexionBBDDUsuarios bdusers = new ConexionBBDDUsuarios(this, "usuarios",null, 1);
        SQLiteDatabase sql = bdusers.getReadableDatabase();
        String[] arguments = new String[] {usuario};
        Cursor c = sql.rawQuery("SELECT * FROM usuarios WHERE email = ? ", arguments);
        if (c.moveToNext()){
            Intent intent = new Intent(Identificacion.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "No existe el usuario", Toast.LENGTH_SHORT).show();
        }
    }
}