package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tapastop.Entidades.Usuario;
import com.example.tapastop.backend.sqlte.Controlador;

public class crearCuenta2 extends AppCompatActivity {

    Button volver;
    Button terminar;

    EditText nombre;
    EditText ap1;
    EditText ap2;
    EditText ubi;
    EditText info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta2);

        nombre =(EditText) findViewById(R.id.nombreCCTxtEdit);
        ap1 =(EditText) findViewById(R.id.apellido1CCTxtxEdit);
        ap2 =(EditText) findViewById(R.id.apellido2CCTxtEdit);
        ubi =(EditText) findViewById(R.id.ubiCCTxtEdit);
        info =(EditText) findViewById(R.id.bioCCTxtEdit);

        Controlador c = new Controlador(this.findViewById(android.R.id.content).getRootView().getContext());

        volver =(Button)findViewById(R.id.volverCC2Btn);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(crearCuenta2.this, crearCuenta.class);
                startActivity(intent);
            }
        });

        terminar =(Button)findViewById(R.id.terminarCC2Btn);
        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario u = c.activo;
                u.setNombre(String.valueOf(nombre.getText()));
                u.setAp1(String.valueOf(ap1.getText()));
                u.setAp2(String.valueOf(ap2.getText()));
                u.setUbi(String.valueOf(ubi.getText()));
                u.setBio(String.valueOf(info.getText()));
                c.crearCuenta2(u);
                Intent intent = new Intent(crearCuenta2.this, VueltaALogin.class);
                startActivity(intent);
            }
        });
    }
}