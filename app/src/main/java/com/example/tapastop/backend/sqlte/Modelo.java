package com.example.tapastop.backend.sqlte;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        return true;
    }
    public boolean login(String username, String password){
        boolean res = false;
        String query = "SELECT password from t_usuarios " +
                "where username = '" + username + "'";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor!=null){
            cursor.moveToFirst();
            //try {
            if(cursor.getColumnIndex("Password") != -1) {
                @SuppressLint("Range") String passw = cursor.getString(cursor.getColumnIndex("Password"));
                if(password.equals(passw)){
                    return true;
                }
            }

//            } catch (Exception e) {
//                return false;
//            }
        }
        return res;
    }
}
