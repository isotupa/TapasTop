package com.example.tapastop.backend.sqlte;

import android.content.Context;

import com.example.tapastop.Entidades.Degustacion;
import com.example.tapastop.Entidades.Plato_comida;
import com.example.tapastop.Entidades.Restaurante;
import com.example.tapastop.Entidades.Usuario;

import java.util.List;
//Esta clase recibe los datos de entrada del usuario
//a traves de la interfaz y llama al modelo para ejecutar la llamada


public class Controlador {
    private Modelo modelo;
    public static Usuario activo;
    public static Restaurante resActual;

    public Controlador(Context c){
        this.modelo = new Modelo(c);
    }

    public boolean crearCuenta(Usuario usuario){
        modelo.crearCuenta(usuario);
        return true;
    }
    public boolean crearCuenta2(Usuario usuario){
        return modelo.crearCuenta2(usuario);
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

    public boolean crearRestaurante(Restaurante restaurante){
        return modelo.crearRestaurante(restaurante);
    }
    public boolean crearPlatoComida(Plato_comida plato_comida){
        return modelo.crearPlatoComida(plato_comida);
    }
    public boolean crearDegustacion(Degustacion degustacion) {
        return modelo.crearDegustacion(degustacion);
    }
    public List<Restaurante> listarRestaurantes(String pattern) {
        return modelo.listar_restaurantes(pattern);
    }
    public List<Degustacion> listarDegustaciones(Usuario usuario) {
        return modelo.listar_degustaciones(usuario);
    }
}
