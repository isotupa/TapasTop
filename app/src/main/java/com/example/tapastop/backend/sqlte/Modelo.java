package com.example.tapastop.backend.sqlte;



import android.database.sqlite.SQLiteDatabase;

import com.example.tapastop.Entidades.Usuario;


//En esta clase estara toda la logica de la app
//
public class Modelo {
    private Database database;
    public Modelo(){
        this.database = new Database(null);
        SQLiteDatabase db = database.getWritableDatabase();
        if(db!=null){
            System.out.println("DataBase Creado correctamente");
        }
    }
    public boolean crearCuenta(Usuario usuario){

        return true;
    }
}
