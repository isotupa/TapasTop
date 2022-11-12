package com.example.tapastop.Entidades;

public class Degustacion {
    private Integer id;
    private String username;
    private Integer id_plato;
    private String calificacion;

    public Degustacion(Integer id, String username, Integer id_plato,String calificacion){
        this.id = id;
        this.username = username;
        this.id_plato = id_plato;
        this.calificacion = calificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId_plato() {
        return id_plato;
    }

    public void setId_plato(Integer id_plato) {
        this.id_plato = id_plato;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }
}
