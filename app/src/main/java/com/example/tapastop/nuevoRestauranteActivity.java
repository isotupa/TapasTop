package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tapastop.Entidades.Degustacion;
import com.example.tapastop.Entidades.Plato_comida;
import com.example.tapastop.Entidades.Restaurante;
import com.example.tapastop.backend.adapters.DegustacionAdapter;
import com.example.tapastop.backend.adapters.PlatoAdapter;
import com.example.tapastop.backend.sqlte.Controlador;

import java.util.ArrayList;
import java.util.List;

public class nuevoRestauranteActivity extends AppCompatActivity {

    EditText nombre;
    EditText dir;

    Button guardar;
    Button cancelar;

    Button nuevo;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_restaurante);

        nombre = findViewById(R.id.restauranteNRTxtEdit2);
        dir = findViewById(R.id.direccionNRTxtEdit);

        guardar = findViewById(R.id.guardarNDBtn2);
        nuevo = findViewById(R.id.nuevoPlatoBtn);

        recyclerView = findViewById(R.id.recyclerPlatos);

        Controlador c = new Controlador(this.findViewById(android.R.id.content).getRootView().getContext());

        // layout for vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(nuevoRestauranteActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Sending reference and data to DegustacionAdapter
        List<Plato_comida> listaDegustaciones = new ArrayList<>();
        listaDegustaciones.add(new Plato_comida(0, "Burger", "Estadounidense", "EEUU", "Normal", "Kuger Bing"));
        listaDegustaciones.add(new Plato_comida(1, "asd", "Estadounidense", "EEUU", "Normal", "Kuger Bing"));
        listaDegustaciones.add(new Plato_comida(2, "sdf", "Estadounidense", "EEUU", "Normal", "Kuger Bing"));
        listaDegustaciones.add(new Plato_comida(3, "dg", "Estadounidense", "EEUU", "Normal", "Kuger Bing"));
        listaDegustaciones.add(new Plato_comida(4, "fgh", "Estadounidense", "EEUU", "Normal", "Kuger Bing"));

        PlatoAdapter adapter = new PlatoAdapter(listaDegustaciones);
        //TODO listar platos
        //PlatoAdapter adapter = new PlatoAdapter(c.listarPlatos());

        // Setting DegustacionAdapter to RecyclerView
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Restaurante res = new Restaurante(nombre.getText().toString(), dir.getText().toString());
                c.crearRestaurante(res);
                Toast.makeText(getApplicationContext(),"¡Restaurante creado!" ,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(nuevoRestauranteActivity.this, MainActivity2.class);
                startActivity(intent);

            }
        });

        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(nuevoRestauranteActivity.this, NuevoPlatoActivity.class);
                intent.putExtra("restaurantName", nombre.getText());
                startActivity(intent);

            }
        });

        cancelar = findViewById(R.id.cancelarNDBtn2);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(nuevoRestauranteActivity.this, MainActivity2.class);
                startActivity(intent);

            }
        });



    }
}