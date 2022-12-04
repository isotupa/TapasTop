package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tapastop.Entidades.Plato_comida;
import com.example.tapastop.backend.adapters.PlatoAdapter;
import com.example.tapastop.backend.sqlte.Controlador;

import java.util.ArrayList;
import java.util.List;

public class RestaurantScreenActivity extends AppCompatActivity {

    TextView name;
    TextView address;

    ImageView img;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_screen);

        name = findViewById(R.id.restaurantNameTxtView);
        address = findViewById(R.id.restaurantAddressTxtView);

        img = findViewById(R.id.restaurantPicImgView);

        if(getIntent().hasExtra("restaurantName")) {
            name.setText(getIntent().getExtras().get("restaurantName") + "");
            address.setText(getIntent().getExtras().get("restaurantAddress") + "");
        }

        recyclerView = findViewById(R.id.recyclerView2);

        Controlador c = new Controlador(this.findViewById(android.R.id.content).getRootView().getContext());

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

        PlatoAdapter adapter = new PlatoAdapter(listaDegustaciones);
        //TODO listar platos
        //PlatoAdapter adapter = new PlatoAdapter(c.listarPlatos());

        // Setting DegustacionAdapter to RecyclerView
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


}