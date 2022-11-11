package com.example.tapastop.Entidades;

public class Usuario {
    private String username;
    private String password;
    private String edad;
    private String email;

    public Usuario(String username,String password,String edad,String email){
        this.username = username;
        this.password = password;
        this.edad = edad;
        this.email = email;
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


}
