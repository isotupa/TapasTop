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
    public boolean crearCuenta2(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("Nombre", usuario.getNombre());
        values.put("Apellido1",usuario.getAp1());
        values.put("Apellido2",usuario.getAp2());
        values.put("Ciudad",usuario.getUbi());
        values.put("Info",usuario.getBio());
        String selection = "Username LIKE ?";
        String[] selectionArgs = { usuario.getUsername() };
        db.update("t_usuarios",values,selection,selectionArgs);
        return true;
    }
    public boolean login(String username, String password){
        boolean res = false;
        String query = "SELECT password from t_usuarios " +
                "where username = '" + username + "'";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor!=null){
            cursor.moveToFirst();
            try {
            if(cursor.getColumnIndex("Password") != -1) {
                @SuppressLint("Range") String passw = cursor.getString(cursor.getColumnIndex("Password"));
                if(password.equals(passw)){
                    return true;
                }
            }
            } catch (Exception e) {
                return false;
            }
        }
        return res;
    }
    public boolean borrar_usuario(Usuario username){
        String query = "DELETE from t_usuarios " +
                       "where username = '" + username.getUsername() + "'";
        db.execSQL(query);
        return true;
    }

    public Usuario getUsuario(String username) {
        Usuario res = new Usuario();
        String query = "SELECT * from t_usuarios " +
                "where username = '" + username + "'";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor!=null){
            cursor.moveToFirst();
            try {
                @SuppressLint("Range") String passw = cursor.getString(cursor.getColumnIndex("Password"));
                @SuppressLint("Range") String edad = cursor.getString(cursor.getColumnIndex("Edad"));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("Email"));
                res.setUsername(username);
                res.setEdad(edad);
                res.setPassword(passw);
                res.setEmail(email);
                return res;
            } catch (Exception e) {
                return null;
            }
        }
        return res;
    }
}
