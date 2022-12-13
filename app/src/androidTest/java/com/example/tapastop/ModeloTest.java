package com.example.tapastop;

import static org.junit.Assert.assertEquals;

import android.app.Instrumentation;
import android.content.Context;

import com.example.tapastop.Entidades.Degustacion;
import com.example.tapastop.Entidades.Plato_comida;
import com.example.tapastop.Entidades.Restaurante;
import com.example.tapastop.Entidades.Usuario;
import com.example.tapastop.backend.sqlte.Modelo;
import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ModeloTest{
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private Modelo modelo;
    private Usuario u;
    private Usuario u10;
    private Usuario u_borrado;
    private Restaurante r;
    private Plato_comida p;
    private Degustacion d;
    public ModeloTest() {
        modelo = new Modelo(appContext);
        u = new Usuario("c","123","25","c@c.com");
        u.setNombre("Diego");
        u_borrado = new Usuario("A","123","25","c@c.com");
        u10 = new Usuario("123","123","25","c@c.com");
        Restaurante r = new Restaurante("KFC","aqui","c");
        Plato_comida p = new Plato_comida(1,"pollo_frito","pollo","Madrid","salado","KFC");
        Degustacion d = new Degustacion(1,"c",1,"10");
        Degustacion d2 = new Degustacion(2,"123",1,"5");
        modelo.crearCuenta(u);
        modelo.crearCuenta2(u);
        modelo.crearCuenta(u10);
        modelo.crearCuenta(u_borrado);
        modelo.crearRestaurante(r);
        modelo.crearPlatoComida(p);
        modelo.crearDegustacion(d);
        modelo.crearDegustacion(d2);
    }

    @Test
    public void crearUsuarioTest() {
       assertEquals(modelo.getUsuario("c").getEmail(),"c@c.com");
    }
    @Test
    public void crearUsuarioTest2() {
        Usuario prueba = new Usuario("prueba","123","18","prueba@prueba.com");
        prueba.setNombre("Diego");
        modelo.crearCuenta(prueba);
        modelo.crearCuenta2(prueba);
        assertEquals(modelo.getUsuario("prueba").getNombre(),"Diego");
    }
    @Test
    public void borrarUsuarioTest() {
        modelo.borrar_usuario(u_borrado);
        assertEquals(modelo.getUsuario("A").getNombre(),null);
    }
    @Test
    public void loginTest() {
        assertEquals(modelo.login("c","123"),true);
    }
    @Test
    public void listar_restaurantesTest() {
        Restaurante r1 = new Restaurante("Goiko","aqui","c");
        Restaurante r2 = new Restaurante("Five","aqui","c");
        assertEquals(modelo.listar_restaurantes("G").size(),1);
    }
    @Test
    public void listar_degustacionesTest() {
        assertEquals(modelo.listar_degustaciones("c").size(),1);
    }
    @Test
    public void getRestauranteTest() {
        assertEquals(modelo.get_Restaurante(1).getNombre(),"KFC");
    }
    @Test
    public void getDegustacionTest() {
        assertEquals(modelo.get_Degustacion(1).getCalificacion(),"10");
    }
    @Test
    public void get_platos_restauranteTest() {
        assertEquals(modelo.get_platos_restaurante("KFC").get(0).getNombre(),"pollo_frito");
    }
    @Test
    public void listar_degustaciones_restauranteTest() {
        assertEquals(Modelo.listar_degustaciones_restaurante(1).get(0).getCalificacion(),"10");
    }
    @Test
    public void valoracion_media_restauranteTest() {
        assertEquals(modelo.valoracion_media_restaurante("KFC"),7.5,0.5);
    }
}
