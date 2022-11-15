package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    EditText calificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_degustacion);

        restaurante = findViewById(R.id.restauranteNDTxtEdit);
        plato = findViewById(R.id.platoNDTxtEdit2);
        calificacion = findViewById(R.id.editTextTextMultiLine);

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
                Toast.makeText(getApplicationContext(),"Degustación creada",Toast.LENGTH_LONG).show();
                Degustacion degustacion = new Degustacion(num++, c.activo.getUsername(), num++, calificacion.getText().toString());
                c.crearDegustacion(degustacion);
                Intent intent = new Intent(NuevaDegustacionActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}