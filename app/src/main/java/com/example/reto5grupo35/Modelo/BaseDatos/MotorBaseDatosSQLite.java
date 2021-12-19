package com.example.reto5grupo35.Modelo.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MotorBaseDatosSQLite extends SQLiteOpenHelper {

    public MotorBaseDatosSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TABLA FAVORITOS
        db.execSQL("CREATE TABLE favoritos (id INT, titulo TEXT,descripcion TEXT)");
        //---- Registros


        //TABLA PRODUCTOS
        db.execSQL("CREATE TABLE productos (imagen INT,titulo TEXT,descripcion TEXT)");

        //---- Registros
        db.execSQL("INSERT INTO productos VALUES (0,'Dama', 'encuentra todo lo relacionado con chaquetas para dama')");
        db.execSQL("INSERT INTO productos VALUES (1,'Caballero', 'encuentra todo lo relaconado con chaquetas para caballero')");
        db.execSQL("INSERT INTO productos VALUES (2,'Niño','encuentra toda clase de cahquetas para niño')");

        //TABLA SERVICIOS
        db.execSQL("CREATE TABLE servicios (imagen INT,titulo TEXT,descripcion TEXT)");
        //---- Registros
            db.execSQL("INSERT INTO servicios VALUES (0,'Domicilios','hacemos entrega de todos tus pedidos a cualquier pate del pais')");
        db.execSQL("INSERT INTO servicios VALUES (1,'Estampados','personaliza tu chaqueta como mas te guste.')");
        db.execSQL("INSERT INTO servicios VALUES (2,'Reparaciones','tu cahqueta favorita esta extropeadad, nosotros te ayudamos y la dejamos como nueva')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE favoritos");
        db.execSQL("DROP TABLE productos");
        db.execSQL("DROP TABLE servicios");
        onCreate(db);

    }
}
