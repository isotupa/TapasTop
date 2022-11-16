package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tapastop.Entidades.Plato_comida;
import com.example.tapastop.backend.sqlte.Controlador;

public class NuevoPlatoActivity extends AppCompatActivity {

    EditText restaurante;
    EditText nombre;

    Button guardar;
    Button cancelar;

    static int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_plato);

        restaurante = findViewById(R.id.restauranteNRTxtEdit);
        nombre = findViewById(R.id.direccionNRTxtEdit2);

        guardar = findViewById(R.id.button2);
        cancelar = findViewById(R.id.button3);

        Controlador c = new Controlador(this.findViewById(android.R.id.content).getRootView().getContext());

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Plato_comida plato = new Plato_comida(x++, nombre.getText().toString(), "India", "india", "curry", "Raja Majal");
                c.crearPlatoComida(plato);
                Intent intent = new Intent(NuevoPlatoActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoPlatoActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });


    }
}