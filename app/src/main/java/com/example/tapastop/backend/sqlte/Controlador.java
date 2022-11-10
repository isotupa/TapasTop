package com.example.tapastop.backend.sqlte;
import com.example.tapastop.Entidades.Usuario;
//Esta clase recibe los datos de entrada del usuario
//a traves de la interfaz y llama al modelo para ejecutar la llamada


public class Controlador {
    public boolean crearCuenta(Usuario usuario){
        //Controlador comprueba Pre condicion mayoria de edad
        if(Integer.parseInt(usuario.getEdad())>=18){
            //llamamos a modelo

        }
        return true;
    }
}
