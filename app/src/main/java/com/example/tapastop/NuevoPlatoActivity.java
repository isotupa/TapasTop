package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tapastop.Entidades.Plato_comida;
import com.example.tapastop.backend.sqlte.Controlador;

import java.util.Random;

public class NuevoPlatoActivity extends AppCompatActivity {

    EditText nombre;
    EditText region;
    EditText sabor;
    EditText tipo;

    Button guardar;
    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_plato);

        nombre = findViewById(R.id.direccionNRTxtEdit2);
        region = findViewById(R.id.regionComida);
        sabor = findViewById(R.id.saborComida);
        tipo = findViewById(R.id.tipoComida);

        guardar = findViewById(R.id.button2);
        cancelar = findViewById(R.id.button3);

        String nameRestaurant = getIntent().getExtras().get("nameRestaurant") + "";

        Controlador c = new Controlador(this.findViewById(android.R.id.content).getRootView().getContext());

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int number = random.nextInt(100000);
                Plato_comida plato = new Plato_comida(number, nombre.getText().toString(), tipo.getText().toString(),
                        region.getText().toString(), sabor.getText().toString(), getIntent().getExtras().get("nameRestaurant")+"");
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