package com.example.tapastop.Entidades;

import java.util.ArrayList;
import java.util.List;

public class Galardones {
    private List tipos = new ArrayList<String>();
    private String username;
    private String tipo;
    private Integer id;
    private Integer degustaciones;

    public List getTipos() {
        return tipos;
    }

    public Galardones(String username, String tipo, Integer id, Integer degustaciones){
        this.username = username;
        this.tipo = tipo;
        this.id = id;
        this.degustaciones = degustaciones;
        tipos.add("Tortilla");
        tipos.add("Paella");
        tipos.add("Pizza");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDegustaciones() {
        return degustaciones;
    }

    public void setDegustaciones(Integer degustaciones) {
        this.degustaciones = degustaciones;
    }


}
