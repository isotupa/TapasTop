package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tapastop.Entidades.Plato_comida;
import com.example.tapastop.backend.adapters.PlatoAdapter;
import com.example.tapastop.backend.adapters.PlatoEvaluableAdapter;
import com.example.tapastop.backend.sqlte.Controlador;

import java.util.ArrayList;
import java.util.List;

public class RestaurantScreenActivity extends AppCompatActivity {

    TextView name;
    TextView address;
    TextView average;

    Button verCartaBtn;
    Button volver;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_screen);

        Controlador c = new Controlador(this.findViewById(android.R.id.content).getRootView().getContext());

        name = findViewById(R.id.restaurantNameTxtView);
        address = findViewById(R.id.restaurantAddressTxtView);
        average = findViewById(R.id.averageRestaurantTxtView);

        verCartaBtn = findViewById(R.id.verCartaBtn);
        volver = findViewById(R.id.volverRSBtn);

        if(getIntent().hasExtra("restaurantName")) {
            name.setText(getIntent().getExtras().get("restaurantName") + "");
            address.setText(getIntent().getExtras().get("restaurantAddress") + "");
            double res = c.valoracion_media_restaurante(getIntent().getExtras().get("restaurantName")+"");
            if(res == -1) average.setText("Sin valoraciones");
            else average.setText("Valoración media: " + res);
        }

        recyclerView = findViewById(R.id.recyclerView2);

        verCartaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantScreenActivity.this, NuevoPlatoActivity.class);
                intent.putExtra("restaurantName", getIntent().getExtras().get("restaurantName") + "");
                intent.putExtra("restaurantAddress", getIntent().getExtras().get("restaurantAddress") + "");
                startActivity(intent);
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantScreenActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        // layout for vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RestaurantScreenActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Sending reference and data to DegustacionAdapter
        List<Plato_comida> listaDegustaciones = new ArrayList<>();
        listaDegustaciones.add(new Plato_comida(0, "Burger", "Estadounidense", "EEUU", "Normal", "Kuger Bing"));
        listaDegustaciones.add(new Plato_comida(1, "asd", "Estadounidense", "EEUU", "Normal", "Kuger Bing"));
        listaDegustaciones.add(new Plato_comida(2, "sdf", "Estadounidense", "EEUU", "Normal", "Kuger Bing"));
        listaDegustaciones.add(new Plato_comida(3, "dg", "Estadounidense", "EEUU", "Normal", "Kuger Bing"));
        listaDegustaciones.add(new Plato_comida(4, "fgh", "Estadounidense", "EEUU", "Normal", "Kuger Bing"));

        //PlatoAdapter adapter = new PlatoAdapter(c.get_platos_restaurante(getIntent().getExtras().get("restaurantName").toString()));
        //address.setText(getIntent().getExtras().get("restaurantName").toString());
        //PlatoAdapter adapter = new PlatoAdapter(listaDegustaciones);
        PlatoEvaluableAdapter adapter = new PlatoEvaluableAdapter(c.get_platos_restaurante(getIntent().getExtras().get("restaurantName") + ""));

        // Setting DegustacionAdapter to RecyclerView
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


}