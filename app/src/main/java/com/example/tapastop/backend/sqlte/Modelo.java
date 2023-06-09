package com.example.tapastop.backend.sqlte;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.IntegerRes;

import com.example.tapastop.Entidades.Degustacion;
import com.example.tapastop.Entidades.Galardones;
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
        values.put("Foto", usuario.getFoto());
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
                "where username = '" + username + "'";
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
                @SuppressLint("Range") byte[] foto = cursor.getBlob(cursor.getColumnIndex("Foto"));
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
                res.setFoto(foto);
                return res;
            } catch (Exception e) {
                return null;
            }
        }
        return res;
    }

    public boolean crearRestaurante(Restaurante restaurante) {
        ContentValues values = new ContentValues();
        values.put("Nombre", restaurante.getNombre());
        values.put("Direccion", restaurante.getDireccion());
        values.put("Username", restaurante.getUsername());
        db.insert("t_restaurante", null, values);
        return true;
    }

    public boolean crearPlatoComida(Plato_comida platoComida) {
        ContentValues values = new ContentValues();
        values.put("id", platoComida.getId());
        values.put("Nombre", platoComida.getNombre());
        values.put("Tipo_comida", platoComida.getTipo_comida());
        values.put("Region", platoComida.getRegion());
        values.put("Sabor", platoComida.getSabor());
        values.put("Descripcion", platoComida.getDescripcion());
        values.put("Foto", platoComida.getFoto());
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
                    @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("Username"));
                    Restaurante restaurante = new Restaurante(nombre, direccion, username);
                    restaurantes.add(restaurante);
                }
                return restaurantes;
            } catch (Exception e) {
                return null;
            }

        }
        return restaurantes;
    }

    //ORDEN MAS RECIENTE
    public List listar_degustaciones(String usuario) {
        List<Degustacion> degustaciones = new ArrayList<Degustacion>();
        String query = "SELECT * from  t_degustacion where Username = '" + usuario + "' ORDER BY id DESC";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") Integer id_plato_comida = cursor.getInt(cursor.getColumnIndex("id_Plato_comida"));
                @SuppressLint("Range") String calificacion = cursor.getString(cursor.getColumnIndex("Calificacion"));
                Degustacion degustacion = new Degustacion(id, usuario, id_plato_comida, calificacion);
                degustaciones.add(degustacion);
            }
            return degustaciones;
        }
        return degustaciones;
    }

    //ORDEN CALIFICACION MAYOR
    public List listar_degustaciones_Orden_calificacion(String usuario) {
        List<Degustacion> degustaciones = new ArrayList<Degustacion>();
        String query = "SELECT * from  t_degustacion where Username = '" + usuario + "' ORDER BY calificacion DESC";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") Integer id_plato_comida = cursor.getInt(cursor.getColumnIndex("id_Plato_comida"));
                @SuppressLint("Range") String calificacion = cursor.getString(cursor.getColumnIndex("Calificacion"));
                Degustacion degustacion = new Degustacion(id, usuario, id_plato_comida, calificacion);
                degustaciones.add(degustacion);
            }
            return degustaciones;
        }
        return degustaciones;
    }

    public static Restaurante get_Restaurante(Integer id_plato) {
        Restaurante res = null;
        String query = "SELECT * from t_restaurante where nombre = " +
                "(Select Restaurante from t_plato_comida where id = " + id_plato + ")";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            try {
                @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("Nombre"));
                @SuppressLint("Range") String direccion = cursor.getString(cursor.getColumnIndex("Direccion"));
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("Username"));
                res = new Restaurante(nombre, direccion, username);
                return res;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
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
        return null;
    }

    // Un método al que le pases un restaurante y te devuelva una lista de sus platos
    public List<Plato_comida> get_platos_restaurante(String restaurante) {
        List<Plato_comida> platos = new ArrayList<Plato_comida>();
        String query = "Select * from t_plato_comida where restaurante = '" + restaurante + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            //System.out.println("cond: " + cursor.moveToFirst());
            while (cursor.moveToNext()) {
                try {
                    @SuppressLint("Range") Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                    @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("Nombre"));
                    @SuppressLint("Range") String tipo_comida = cursor.getString(cursor.getColumnIndex("Tipo_comida"));
                    @SuppressLint("Range") String region = cursor.getString(cursor.getColumnIndex("Region"));
                    @SuppressLint("Range") String sabor = cursor.getString(cursor.getColumnIndex("Sabor"));
                    @SuppressLint("Range") String descripcion = cursor.getString(cursor.getColumnIndex("Descripcion"));
                    @SuppressLint("Range") byte[] img = cursor.getBlob(cursor.getColumnIndex("Foto"));
                    Plato_comida plato = new Plato_comida(id, nombre, tipo_comida, region, sabor, restaurante);
                    plato.setDescripcion(descripcion);
                    plato.setFoto(img);
                    platos.add(plato);
                } catch (Exception e) {
                    return null;
                }
            }
            //platos.add(new Plato_comida(90, "papas","eeuu", "eeuu", "eeuu", "MacDonalds"));
            cursor.close();
            return platos;
        }
        return platos;
    }

    //Devuelve todas las degustaciones de un plato de comida
    public static List<Degustacion> listar_degustaciones_restaurante(int plato) {
        List<Degustacion> degustaciones = new ArrayList<Degustacion>();
        String query = "SELECT * from  t_degustacion where id_PLato_comida = '" + plato + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("Username"));
                @SuppressLint("Range") String calificacion = cursor.getString(cursor.getColumnIndex("Calificacion"));
                Degustacion degustacion = new Degustacion(id, username, plato, calificacion);
                degustaciones.add(degustacion);
            }
            return degustaciones;
        }
        return degustaciones;
    }

    // Un método al q le pases un restaurante y te devuelva la media de sus valoraciones
    public double valoracion_media_restaurante(String restaurante) {
        Integer media_res = 0;
        List<Plato_comida> platos = get_platos_restaurante(restaurante);
        if(platos.size() == 0) return -1;
        for (int i = 0; i < platos.size(); i++) {
            int media_plato = 0;
            List<Degustacion> degustaciones = listar_degustaciones_restaurante(platos.get(i).getId());
            if (degustaciones.size() == 0) continue;
            for (int j = 0; j < degustaciones.size(); j++) {
                media_plato += Integer.parseInt(degustaciones.get(j).getCalificacion());
            }
            media_res += (media_plato / degustaciones.size());
        }
        media_res = (media_res / platos.size());
        return media_res;
    }

    private double media(List<Degustacion> l) {
        double res = 0;
        if (l.size() == 0) return -1;
        for (Degustacion d : l) {
            res += Integer.parseInt(d.getCalificacion());
        }
        System.out.println("avg:" + res);
        return res;
    }

    //Un método para listar los restaurantes creados por un usuario
    public List<Restaurante> restaurantes_usuarios(String username) {
        ArrayList<Restaurante> restaurantes = new ArrayList<Restaurante>();
        String query = "SELECT * from  t_restaurante where username = '" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                //@SuppressLint("Range") Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("Nombre"));
                @SuppressLint("Range") String direccion = cursor.getString(cursor.getColumnIndex("Direccion"));
                Restaurante restaurante = new Restaurante(nombre, direccion, username);
                restaurantes.add(restaurante);
            }
            return restaurantes;
        }
        return restaurantes;
    }

    //un método al q le pases un correo y te devuelva la cuenta asociada
    public Usuario getUsuario_mail(String correo) {
        Usuario res = new Usuario();
        String query = "SELECT * from t_usuarios " +
                "where username = '" + correo + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            try {
                @SuppressLint("Range") String passw = cursor.getString(cursor.getColumnIndex("Password"));
                @SuppressLint("Range") String edad = cursor.getString(cursor.getColumnIndex("Edad"));
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("Username"));
                @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("Nombre"));
                @SuppressLint("Range") String ap1 = cursor.getString(cursor.getColumnIndex("Apellido1"));
                @SuppressLint("Range") String ap2 = cursor.getString(cursor.getColumnIndex("Apellido2"));
                @SuppressLint("Range") String ciudad = cursor.getString(cursor.getColumnIndex("Ciudad"));
                @SuppressLint("Range") byte[] foto = cursor.getBlob(cursor.getColumnIndex("Foto"));
                @SuppressLint("Range") String info = cursor.getString(cursor.getColumnIndex("Info"));
                res.setUsername(username);
                res.setEdad(edad);
                res.setPassword(passw);
                res.setEmail(correo);
                res.setNombre(nombre);
                res.setAp1(ap1);
                res.setAp2(ap2);
                res.setUbi(ciudad);
                res.setBio(info);
                res.setFoto(foto);
                return res;
            } catch (Exception e) {
                return null;
            }
        }
        return res;
    }

    //un método al q le pases un plato de comida y el nombre de su restaurante y te devuelva el id del plato
    @SuppressLint("Range")
    public static Integer get_id_plato(String nombre_plato, String nombre_restaurante) {
        Integer id_plato = -1;
        String query = "Select * from t_plato_comida where restaurante = '" + nombre_restaurante + "' " +
                "and nombre = '" + nombre_plato + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                id_plato = cursor.getInt(cursor.getColumnIndex("id"));
            } catch (Exception e) {
                return null;
            }
            cursor.close();
            return id_plato;
        }
        return id_plato;
    }

    //Añadir amigo
    public void anadir_amigo(String username1, String username2) {
        ContentValues values = new ContentValues();
        values.put("Username1", username1);
        values.put("Username2", username2);
        db.insert("t_amigos", null, values);

    }

    //Borrar Amigo
    public void eliminar_amigo(String username1, String username2) {
        String query = "DELETE from t_amigos " +
                "where username1 = '" + username1 + "'" +
                "and username2 = '" + username2 + "'";
        db.execSQL(query);
    }

    public static String get_plato_comida(Integer id_plato) {
        String res = null;
        String query = "SELECT * from t_plato_comida where id = " + id_plato;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            try {
                @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("Nombre"));

                return nombre;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

// listar todos los posibles galardones -> Atributo de galardones...


    // subir número de degustaciones de x usuario por cantidad c
    public static void aumentar_degustacion_galardon(String username, String tipo, int c) {
        String query = "SELECT * from t_galardones where tipo = '" + tipo + "' and username = '" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
            try {
                //GET numero degustciones
                @SuppressLint("Range") Integer degustacion = cursor.getInt(cursor.getColumnIndex("Degustaciones"));
                degustacion += c;
                //UPDATE numero degustaciones
                ContentValues values = new ContentValues();
                values.put("Degustaciones", degustacion);
                String selection = "Username LIKE ? and Tipo LIKE ?";
                String[] selectionArgs = {username, tipo};
                db.update("t_galardones", values, selection, selectionArgs);

            } catch (Exception e) {

            }
        }
    }

    //Devuelve lista de todos los galardones de un usuario
    public static List<Galardones> get_galardones(String username) {
        List<Galardones> galardones = new ArrayList<>();
        String query = "SELECT * from t_galardones where username = '"+username+"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            while (cursor.moveToNext())
            try {
                //GET numero degustciones
                @SuppressLint("Range") Integer degustacion = cursor.getInt(cursor.getColumnIndex("Degustaciones"));
                @SuppressLint("Range") Integer nivel = cursor.getInt(cursor.getColumnIndex("Nivel"));
                @SuppressLint("Range") String tipo = cursor.getString(cursor.getColumnIndex("Tipo"));
                @SuppressLint("Range") Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                Galardones galardon = new Galardones(username,tipo,id,degustacion,nivel);
                galardones.add(galardon);
            } catch (Exception e) {

            }
            return galardones;
        }
        return galardones;
    }

    public static void  nuevo_galardon(Galardones g){
        ContentValues values = new ContentValues();
        values.put("Username",g.getUsername());
        values.put("Tipo", g.getTipo());
        values.put("id", g.getId());
        values.put("Degustaciones", g.getDegustaciones());
        values.put("Nivel",g.getNivel());
        db.insert("t_galardones", null, values);
    }
}