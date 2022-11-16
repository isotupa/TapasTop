package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.tapastop.Entidades.Degustacion;
import com.example.tapastop.backend.sqlte.Controlador;
import com.example.tapastop.ui.notifications.NotificationsFragment;

public class NuevaDegustacionActivity extends AppCompatActivity {

    static int num = 0;

    Button cancelar;
    Button guardar;

    EditText restaurante;
    EditText plato;
    RatingBar calificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_degustacion);

        int rating = 0;

        restaurante = findViewById(R.id.restauranteNDTxtEdit);
        plato = findViewById(R.id.platoNDTxtEdit2);
        calificacion = findViewById(R.id.ratingBar2);

        Controlador c = new Controlador(this.findViewById(android.R.id.content).getRootView().getContext());

        cancelar =(Button)findViewById(R.id.cancelarNDBtn);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevaDegustacionActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        guardar =(Button)findViewById(R.id.guardarNDBtn);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int res;
                try {

                    if(restaurante.getText().length() == 0 || plato.getText().length() == 0) {
                        Toast.makeText(getApplicationContext(),"Todos los campos deben ser rellenados",Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Degustación creada",Toast.LENGTH_LONG).show();
                        Degustacion degustacion = new Degustacion(num++, c.activo.getUsername(), num++, (int)(calificacion.getRating())+"");
                        c.crearDegustacion(degustacion);
                        Intent intent = new Intent(NuevaDegustacionActivity.this, MainActivity2.class);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Descripción debe ser un número del 1 al 5",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}