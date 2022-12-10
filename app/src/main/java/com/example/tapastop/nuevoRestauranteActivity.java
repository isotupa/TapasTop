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

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_restaurante);

        nombre = findViewById(R.id.restauranteNRTxtEdit2);
        dir = findViewById(R.id.direccionNRTxtEdit);

        guardar = findViewById(R.id.guardarNDBtn2);

        Controlador c = new Controlador(this.findViewById(android.R.id.content).getRootView().getContext());

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Restaurante res = new Restaurante(nombre.getText().toString(), dir.getText().toString(),c.getUsuario(c.getUser_a()).getUsername());
                c.crearRestaurante(res);
                Toast.makeText(getApplicationContext(),"¡Restaurante creado!" ,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(nuevoRestauranteActivity.this, MainActivity2.class);
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