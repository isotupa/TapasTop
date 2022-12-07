package com.example.tapastop.Entidades;

public class Restaurante {
    String nombre;
    String direccion;
    String username;
    public Restaurante(String nombre, String direccion, String username){
        this.nombre = nombre;
        this.direccion = direccion;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
