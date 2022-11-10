package com.example.tapastop.backend.sqlte;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "tapas_top.db";
    public static final String TABLE_USUARIOS = "t_usuarios";
    public static final String TABLE_AMIGOS = "t_amigos";
    public static final String TABLE_RESTAURANTE = "t_restaurante";
    public static final String TABLE_PLATO_COMIDA = "t_plato_comida";
    public static final String TABLE_DEGUSTACION = "t_degustacion";


    public database(@Nullable Context context){
        super(context,DATABASE_NOMBRE,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USUARIOS +"(" +
                "Username TEXT PRIMARY KEY UNIQUE," +
                "Password TEXT NOT NULL," +
                "Edad TEXT NOT NULL," +
                "Email TEXT NOT NULL,"+
                "Nombre TEXT," +
                "Apellidos TEXT," +
                "Foto BLOB," +
                "Ciudad TEXT," +
                "Info TEXT )");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_RESTAURANTE+"(" +
                "Nombre TEXT PRIMARY KEY UNIQUE," +
                "Direccion TEXT NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PLATO_COMIDA +"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nombre TEXT NOT NULL,"+
                "Tipo_comida TEXT NOT NULL," +
                "Region TEXT NOT NULL," +
                "Sabor TEXT NOT NULL," +
                "Descrpcion TEXT," +
                "Foto BLOB)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DEGUSTACION +"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT NOT NULL," +
                "id_Plato_comida INTEGER NOT NULL," +
                "Calificacion INTEGER NOT NULL ," +
                "FOREIGN KEY(Username) REFERENCES TABLE_USUARIOS(Username)," +
                "FOREIGN KEY(Plato_comida) REFERENCES TABLE_PLATO_COMIDA(id))");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_AMIGOS +"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username1 TEXT NOT NULL," +
                "Username2 TEXT NOT NULL ," +
                "FOREIGN KEY(Username1) REFERENCES TABLE_USUARIOS(Username)," +
                "FOREIGN KEY(Username2) REFERENCES TABLE_USUARIOS(Username))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
  /*  public static void main(String args[]){
        onCreate()
    }*/
}
