package com.example.myapplication;

import static com.example.myapplication.ComandosSQL.DESCRIP;
import static com.example.myapplication.ComandosSQL.FECHA;
import static com.example.myapplication.ComandosSQL.TITULO;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.Adaptadores.prendasOverview;
import com.example.myapplication.Entidades.Prenda;

public class MainActivity extends AppCompatActivity {
    //Inicialización de atributos
    ArrayList<Prenda> listaPrendas = new ArrayList<Prenda>();
    prendasOverview prendaOverview ;
    ConexionBBDDLocal ddbb;

    /*ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            // Add same code that you want to add in onActivityResult
                            //method
                            if (result.getResultCode() == RESULT_OK) {
                                Prenda pr = result.getData().getStringExtra("variable_01");
                            }
                        }
                    });*/


    // cuando se inicializa la clase
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // se inicializa una instancia de la clase y se muestra la interfaz activity_main
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //prueba manual
            //Prenda p = new Prenda("Titulo ejemplo", "Descripcion", "17-05-2022", R.drawable.ic_launcher_background);
            //Prenda p1 = new Prenda("Titulo ejemplo2", "Descripcion2", "13-05-2022", R.drawable.ic_launcher_background);
            //listaPrendas.add(p);
            //listaPrendas.add(p1);
        // se llama al método crearLista que muestra en la interfaz la lista de prendas que hay
        //ya registradas anteriormente
        crearLista();
        prendaOverview = new prendasOverview(listaPrendas,getApplicationContext());
        ListView prendas = (ListView) findViewById(R.id.lPrendas);
        prendas.setAdapter(prendaOverview);

        Button nuevaPrenda = findViewById(R.id.botonCrearPrenda);
        nuevaPrenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IntroducirPrendas.class);
                startActivity(intent);
                finish();
                //mirar pdf content e intent y utilizar startActivityIntent
            }
        });
    }

    private void crearLista(){
        ListView prendas = (ListView) findViewById(R.id.lPrendas);
        listaPrendas = new ArrayList<Prenda>();
        // se conecta con una instancia de la clase que conecta con la BD, en este caso DBprendas
        ddbb = new ConexionBBDDLocal(getApplicationContext(), "prendas", null, 1);
        //listaPrendas = bbdd.obtenerTodasLasPrendas();

        // se llama al método en el que se obtienen todas las prendas de la DBprendas
        obtenerTodasLasPrendas();
    }

    public void obtenerTodasLasPrendas(){
        SQLiteDatabase db = ddbb.getReadableDatabase();
        Prenda p = null;
        // se obtienen todos los datos de las prendas
        Cursor c = db.rawQuery("SELECT * FROM prendas", null);
        while(c.moveToNext()){
            // se crean nuevas instancias de prenda con los datos obtenidos
            p = new Prenda(c.getString(1), c.getString(2), c.getString(0), R.drawable.ic_launcher_background);
            // se añaden en la lista que vamos a mostrar por pantalla en la interfaz
            listaPrendas.add(p);
        }

    }

}