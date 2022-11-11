package com.example.tapastop.backend.sqlte;

import android.content.Context;

import com.example.tapastop.Entidades.Usuario;
//Esta clase recibe los datos de entrada del usuario
//a traves de la interfaz y llama al modelo para ejecutar la llamada


public class Controlador {
    private Modelo modelo;
    public static Usuario activo;

    public Controlador(Context c){
        this.modelo = new Modelo(c);
    }

    public boolean crearCuenta(Usuario usuario){
        //Controlador comprueba Pre condicion mayoria de edad
        if(Integer.parseInt(usuario.getEdad())>=18){
            //llamamos a modelo
            modelo.crearCuenta(usuario);
        }
        return true;
    }

    public boolean login(String username, String pw) {
        return modelo.login(username, pw);
    }

    public boolean borrar_usuario(Usuario username){
        return modelo.borrar_usuario(username);
    }

    public Usuario getUsuario(String username) {
        return modelo.getUsuario(username);
    }

}
