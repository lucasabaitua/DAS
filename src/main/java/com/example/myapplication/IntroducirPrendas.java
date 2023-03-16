package com.example.myapplication;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterButton;

import com.example.myapplication.Entidades.Prenda;

import java.time.temporal.ValueRange;
import java.util.Date;

public class IntroducirPrendas extends AppCompatActivity {

    EditText textoTitulo;
    EditText textoDescrip;
    EditText fecha;
    ImageFilterButton img;

    Prenda prenda;
    private ConexionBBDDLocal mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inytoducir_prendas);

        // se declaran variables para los campos donde se introduce texto
        textoTitulo = findViewById(R.id.titulo_edit_text);
        textoDescrip = findViewById(R.id.desc_edit_text);
        fecha = findViewById(R.id.editTextDate);
        img = findViewById(R.id.boton_añadir_imagen);

        /*Bundle obj = getIntent().getExtras();
        prenda = null;
        if (obj!=null){
            prenda = (Prenda) obj.getSerializable("prenda");
            textoTitulo.setText(prenda.getTituloPrenda());
            textoDescrip.setText(prenda.getDescripcion());
            fecha.setText(prenda.getFechaColgado());
            img.setImageResource(prenda.getFoto());
        }*/
        mDatabaseHelper = new ConexionBBDDLocal(getApplicationContext(), "prendas", null, 1);
        Button nueva_prenda = findViewById(R.id.button_guardar);
        nueva_prenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = textoTitulo.getText().toString();
                String desc = textoDescrip.getText().toString();
                String fechaP = fecha.getText().toString();
                //int imagen = img.get;
                if (titulo.isEmpty()||fechaP.isEmpty()||desc.isEmpty()){
                    Toast.makeText(IntroducirPrendas.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    long rowId = mDatabaseHelper.anadir_prenda(titulo, desc, fechaP);
                    if (rowId==-1){
                        Toast.makeText(IntroducirPrendas.this, "Introduccion de prenda fallida", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(IntroducirPrendas.this, "Se ha introducido la prenda", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(IntroducirPrendas.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

            }
        });

        Button cancelar = findViewById(R.id.button_cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /*
    public void onClick(View view){
        switch (view.getId()){
            //Si se pulsa el boton guardar
            case R.id.button_guardar:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDatabaseHelper = new ConexionBBDDLocal(getApplicationContext(), "prendas", null, 1);
                        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(ComandosSQL.TITULO, textoTitulo.getText().toString());
                        values.put(ComandosSQL.DESCRIP, textoDescrip.getText().toString());
                        values.put(ComandosSQL.FECHA, fecha.getText().toString());
                        //values.put(ComandosSQL.FOTO, img.toString());
                        //Si existe la prenda se actualizan los datos de la misma
                        if (prenda != null){
                            String f = prenda.getFechaColgado();
                            //db.update(ComandosSQL.NOM_TABLA, values, ComandosSQL.FECHA + "=", f);
                            db.execSQL("UPDATE " + ComandosSQL.NOM_TABLA + " SET " +
                                    ComandosSQL.TITULO + " = '" + textoTitulo.getText() + "' ,"
                                    + ComandosSQL.DESCRIP + " = '" + textoDescrip.getText()
                                    + "' WHERE " + ComandosSQL.FECHA + " = " + prenda.getFechaColgado());
                        } // Si no existe se introduce en la BBDD con fecha actual
                        else{
                            values.put(ComandosSQL.FECHA, new Date().getTime());
                            db.insert(ComandosSQL.NOM_TABLA, ComandosSQL.FECHA, values);
                        }
                        db.close();
                        Toast.makeText(IntroducirPrendas.this, "Se ha guardado la prenda", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
             //Si se pulsa el boton de cancelar nos lleva de vuela a la pagina principal
            case R.id.button_cancelar:
                finish();
        }*/




}
