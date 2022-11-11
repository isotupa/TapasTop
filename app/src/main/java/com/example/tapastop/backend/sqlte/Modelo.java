package com.example.tapastop.backend.sqlte;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.tapastop.Entidades.Usuario;

//En esta clase estara toda la logica de la app
public class Modelo {
    private Database database;
    SQLiteDatabase db;
    public Modelo(Context c){
        this.database = new Database(c);
        db = database.getWritableDatabase();
        if(db!=null){
            System.out.println("DataBase Creado correctamente");
        }
    }
    public boolean crearCuenta(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("Username", usuario.getUsername());
        values.put("Edad",usuario.getEdad());
        values.put("Email",usuario.getEmail());
        values.put("Password",usuario.getPassword());
        db.insert("t_usuarios",null,values);
      /*  String query = "INSERT INTO t_usuarios Values (" +
                usuario.getUsername() + "," +
                usuario.getPassword()+"," +
                usuario.getEdad()+"," +
                usuario.getEmail()+")";
        db.execSQL(query);*/
        return true;
    }
}
