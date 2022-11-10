package com.example.tapastop.backend.sqlte;



import android.database.sqlite.SQLiteDatabase;

import com.example.tapastop.MainActivity2;

import kotlin.io.ConsoleKt;

//En esta clase estara toda la logica de la app
//
public class Modelo {
    private Database database;
    public Modelo(){
        this.database = new Database(this);
        SQLiteDatabase db = database.getWritableDatabase();
        if(db!=null){
            System.out.println("DataBase Creado correctamente");
        }
    }
}
