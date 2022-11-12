package com.example.tapastop.Entidades;

public class Plato_comida {
    private Integer id;
    private String nombre;
    private String tipo_comida;
    private String region;
    private String sabor;
    private String descripcion = "";
    //FOTO
    private String restaurante;

    public Plato_comida(Integer id, String nombre,String tipo_comida,String region,String sabor, String restaurante){
        this.id = id;
        this.nombre = nombre;
        this.tipo_comida = tipo_comida;
        this.region = region;
        this.sabor = sabor;
        this.restaurante = restaurante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_comida() {
        return tipo_comida;
    }

    public void setTipo_comida(String tipo_comida) {
        this.tipo_comida = tipo_comida;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }
}
