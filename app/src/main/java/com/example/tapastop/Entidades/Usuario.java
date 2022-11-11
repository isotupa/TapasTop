package com.example.tapastop.Entidades;

public class Usuario {
    private String username;
    private String password;
    private String edad;
    private String email;
    private String nombre;
    private String ap1;
    private String ap2;
    private String ubi;
    private String bio;


    public Usuario(String username,String password,String edad,String email){
        this.username = username;
        this.password = password;
        this.edad = edad;
        this.email = email;
    }

    public Usuario(){    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp1() {
        return ap1;
    }

    public void setAp1(String ap1) {
        this.ap1 = ap1;
    }

    public String getAp2() {
        return ap2;
    }

    public void setAp2(String ap2) {
        this.ap2 = ap2;
    }

    public String getUbi() {
        return ubi;
    }

    public void setUbi(String ubi) {
        this.ubi = ubi;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEdad(){
        return this.edad;
    }
    public String getUsername(){
        return this.username;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
