package com.example.tapastop.backend.sqlte;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tapastop.Entidades.Degustacion;
import com.example.tapastop.Entidades.Plato_comida;
import com.example.tapastop.Entidades.Restaurante;
import com.example.tapastop.Entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

//En esta clase estara toda la logica de la app
public class Modelo {
    private Database database;
    static SQLiteDatabase db;

    public Modelo(Context c) {
        this.database = new Database(c);
        db = database.getWritableDatabase();
        if (db != null) {
            System.out.println("DataBase Creado correctamente");
        }
    }

    public boolean crearCuenta(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("Username", usuario.getUsername());
        values.put("Edad", usuario.getEdad());
        values.put("Email", usuario.getEmail());
        values.put("Password", usuario.getPassword());
        db.insert("t_usuarios", null, values);
        return true;
    }

    public boolean crearCuenta2(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("Nombre", usuario.getNombre());
        values.put("Apellido1", usuario.getAp1());
        values.put("Apellido2", usuario.getAp2());
        values.put("Ciudad", usuario.getUbi());
        values.put("Info", usuario.getBio());
        String selection = "Username LIKE ?";
        String[] selectionArgs = {usuario.getUsername()};
        db.update("t_usuarios", values, selection, selectionArgs);
        return true;
    }

    public boolean login(String username, String password) {
        boolean res = false;
        String query = "SELECT password from t_usuarios " +
                "where username = '" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            try {
                if (cursor.getColumnIndex("Password") != -1) {
                    @SuppressLint("Range") String passw = cursor.getString(cursor.getColumnIndex("Password"));
                    if (password.equals(passw)) {
                        return true;
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        return res;
    }

    public boolean borrar_usuario(Usuario username) {
        String query = "DELETE from t_usuarios " +
                "where username = '" + username.getUsername() + "'";
        db.execSQL(query);
        return true;
    }

    public Usuario getUsuario(String username) {
        Usuario res = new Usuario();
        String query = "SELECT * from t_usuarios " +
                "where username = '" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            try {
                @SuppressLint("Range") String passw = cursor.getString(cursor.getColumnIndex("Password"));
                @SuppressLint("Range") String edad = cursor.getString(cursor.getColumnIndex("Edad"));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("Email"));
                @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("Nombre"));
                @SuppressLint("Range") String ap1 = cursor.getString(cursor.getColumnIndex("Apellido1"));
                @SuppressLint("Range") String ap2 = cursor.getString(cursor.getColumnIndex("Apellido2"));
                @SuppressLint("Range") String ciudad = cursor.getString(cursor.getColumnIndex("Ciudad"));
                @SuppressLint("Range") String info = cursor.getString(cursor.getColumnIndex("Info"));
                res.setUsername(username);
                res.setEdad(edad);
                res.setPassword(passw);
                res.setEmail(email);
                res.setNombre(nombre);
                res.setAp1(ap1);
                res.setAp2(ap2);
                res.setUbi(ciudad);
                res.setBio(info);
                return res;
            } catch (Exception e) {
                return null;
            }
        }
        return res;
    }

    public static boolean crearRestaurante(Restaurante restaurante) {
        ContentValues values = new ContentValues();
        values.put("Nombre", restaurante.getNombre());
        values.put("Direccion", restaurante.getDireccion());
        db.insert("t_restaurante", null, values);
        return true;
    }

    public static boolean crearPlatoComida(Plato_comida platoComida) {
        ContentValues values = new ContentValues();
        values.put("id", platoComida.getId());
        values.put("Nombre", platoComida.getNombre());
        values.put("Tipo_comida", platoComida.getTipo_comida());
        values.put("Region", platoComida.getRegion());
        values.put("Sabor", platoComida.getSabor());
        values.put("Descripcion", platoComida.getDescripcion());
        //FOTO
        values.put("Restaurante", platoComida.getRestaurante());
        db.insert("t_plato_comida", null, values);
        return true;
    }

    public boolean crearDegustacion(Degustacion degustacion) {
        ContentValues values = new ContentValues();
        values.put("id", degustacion.getId());
        values.put("Username", degustacion.getUsername());
        values.put("id_Plato_comida", degustacion.getId_plato());
        values.put("Calificacion", degustacion.getCalificacion());
        db.insert("t_degustacion", null, values);
        return true;
    }

    public List listar_restaurantes(String pattern) {
        List<Restaurante> restaurantes = new ArrayList<Restaurante>();
        String query = "SELECT * from  t_restaurante where nombre like '%" + pattern + "%'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) { //Deberia llevarnos al primer elemento
                    @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("Nombre"));
                    @SuppressLint("Range") String direccion = cursor.getString(cursor.getColumnIndex("Direccion"));
                    Restaurante restaurante = new Restaurante(nombre, direccion);
                    restaurantes.add(restaurante);
                }
                return restaurantes;
            } catch (Exception e) {
                return null;
            }

        }
        return restaurantes;
    }

    public List listar_degustaciones(Usuario usuario) {
        List<Degustacion> degustaciones = new ArrayList<Degustacion>();
        String query = "SELECT * from  t_degustacion where Username = '" + usuario.getUsername() + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") Integer id_plato_comida = cursor.getInt(cursor.getColumnIndex("id_Plato_comida"));
                @SuppressLint("Range") String calificacion = cursor.getString(cursor.getColumnIndex("Calificacion"));
                Degustacion degustacion = new Degustacion(id, usuario.getUsername(), id_plato_comida, calificacion);
                degustaciones.add(degustacion);
            }
            return degustaciones;
        }
        return degustaciones;
    }

    public static Restaurante get_Restaurante(Integer id_plato) {
        Restaurante res = null;
        String query = "SELECT * from t_restaurante where nombre = " +
                "(Select nombre from t_plato_comida where id = " + id_plato + ")";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            try {
                @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("Nombre"));
                @SuppressLint("Range") String direccion = cursor.getString(cursor.getColumnIndex("Direccion"));
                res = new Restaurante(nombre,direccion);
                return res;
            } catch (Exception e) {
                return null;
            }
        }
        return res;
    }

    public Degustacion get_Degustacion(Integer id_plato) {
        Degustacion des = null;
        String query = "SELECT * from t_degustacion " +
                "where id_plato_comida = " + id_plato;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            try {
                @SuppressLint("Range") Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("Username"));
                // @SuppressLint("Range") String id_plato = cursor.getInt(cursor.getColumnIndex("id_plato"));
                @SuppressLint("Range") String calificacion = cursor.getString(cursor.getColumnIndex("Calificacion"));
                des = new Degustacion(id, username, id_plato, calificacion);
                return des;
            } catch (Exception e) {
                return null;
            }
        }
        return des;
    }

}
