package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tapastop.Entidades.Degustacion;
import com.example.tapastop.Entidades.Plato_comida;
import com.example.tapastop.Entidades.Usuario;
import com.example.tapastop.backend.adapters.DegustacionAdapter;
import com.example.tapastop.backend.adapters.PlatoEvaluableAdapter;
import com.example.tapastop.backend.sqlte.Controlador;

import java.util.ArrayList;
import java.util.List;

public class FavFoodActivity extends AppCompatActivity {

    RecyclerView rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_food);

        rec = findViewById(R.id.rec);

        Controlador c = new Controlador(this.findViewById(android.R.id.content).getRootView().getContext());
        Usuario u = c.getUsuario(c.getUser_a());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FavFoodActivity.this);
        rec.setLayoutManager(linearLayoutManager);

        List<Degustacion> listaDegustaciones = new ArrayList<>();
        DegustacionAdapter adapter = new DegustacionAdapter(c.listar_degustaciones_Orden_calificacion(u.getUsername()));

        rec.setAdapter(adapter);
        rec.setItemAnimator(new DefaultItemAnimator());
    }
}