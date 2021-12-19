package com.example.reto5grupo35.Modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reto5grupo35.Modelo.BaseDatos.MotorBaseDatosSQLite;
import com.example.reto5grupo35.R;


import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    ArrayList<Entidad> lista_items;
    Context context;

    // CONEXION A LA BASE DE DATOS: SQLite
    MotorBaseDatosSQLite conectar;

    public Adaptador(ArrayList<Entidad> lista_items, Context context) {
        this.lista_items = lista_items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lista_items.size(); // Devuelve cuantos elelmentos hay en la lista
    }

    @Override
    public Object getItem(int posicion) {
        return lista_items.get(posicion); // devuelve la posicion del item
    }

    @Override
    public long getItemId(int posicion) {
        return 0; // No lo vamos a trabajar
    }


    @Override
    public View getView(int posicion, View v, ViewGroup viewGroup) {

        Entidad datosItem = (Entidad) getItem(posicion);

        v = LayoutInflater.from(context).inflate(R.layout.item, null);
        //-------------------------------------------------------------------

        ImageView imagen = (ImageView)v.findViewById(R.id.imagen_item);
        TextView titulo = (TextView)v.findViewById(R.id.titulo_item);
        TextView descripcion = (TextView)v.findViewById(R.id.descripcion_item);


        //---------------------------------------------------------------------------------
        conectar = new MotorBaseDatosSQLite(context,"QuieroMiChaqueta", null, 1);
        SQLiteDatabase db_escribir = conectar.getWritableDatabase();

        //---------------------------------------------------------------------------------

        Button boton1 = (Button) v.findViewById(R.id.boton1_item);
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Guardado en favoritos", Toast.LENGTH_LONG).show();
                db_escribir.execSQL("INSERT INTO favoritos VALUES (1, '"+datosItem.getTitulo()+"', '"+datosItem.getDescripcion()+"')");
            }
        });

        CheckBox favoritos = (CheckBox) v.findViewById(R.id.favoritos1_item);
        CheckBox separar = (CheckBox) v.findViewById(R.id.separar1_item);

        imagen.setImageResource(datosItem.getImagen());
        titulo.setText(datosItem.getTitulo());
        descripcion.setText(datosItem.getDescripcion());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "item:" + titulo.getText(), Toast.LENGTH_LONG ).show();
            }
        });

        //-------------------------------------------------------------------
        return v;
    }

}