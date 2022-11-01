package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class crearCuenta2 extends AppCompatActivity {

    Button volver;
    Button terminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta2);

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
                Intent intent = new Intent(crearCuenta2.this, VueltaALogin.class);
                startActivity(intent);
            }
        });
    }
}