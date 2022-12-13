package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.tapastop.Entidades.Degustacion;
import com.example.tapastop.Entidades.Plato_comida;
import com.example.tapastop.Entidades.Restaurante;
import com.example.tapastop.Entidades.Usuario;
import com.example.tapastop.backend.sqlte.Controlador;
import com.example.tapastop.backend.sqlte.Modelo;
import com.example.tapastop.ui.notifications.NotificationsFragment;

import java.util.List;
import java.util.Random;

public class NuevaDegustacionActivity extends AppCompatActivity {

    static int num = 0;

    Button cancelar;
    Button guardar;

    AutoCompleteTextView restaurante;
    AutoCompleteTextView plato;
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
        Usuario u = c.getUsuario(c.getUser_a());

        List<Restaurante> all = c.listarRestaurantes("");
        String[] s = new String[all.size()];

        for(int i = 0; i < all.size(); i++) {
            s[i] = all.get(i).getNombre();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, s);
        restaurante.setAdapter(adapter);


        List<Plato_comida> allPlatos = c.get_platos_restaurante(restaurante.getText().toString());
        String[] ss = new String[allPlatos.size()];

        for(int i = 0; i < allPlatos.size(); i++) {
            ss[i] = allPlatos.get(i).getNombre();
        }

        ArrayAdapter<String> adapters = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, ss);
        plato.setAdapter(adapters);

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
                try {
                    if(restaurante.getText().length() == 0 || plato.getText().length() == 0) {
                        Toast.makeText(getApplicationContext(),"Todos los campos deben ser rellenados",Toast.LENGTH_LONG).show();
                    } else {
                        Integer idPlato = Modelo.get_id_plato(plato.getText().toString(), restaurante.getText().toString());
                        if(idPlato == -1) {
                            Toast.makeText(getApplicationContext(),"Este plato o este restaurante no existen",Toast.LENGTH_LONG).show();
                        } else {
                            //if(plato.getText().toString().equals("Paella")) Modelo.aumentar_degustacion_galardon(u.getUsername(), "Paella", 1);
                            Toast.makeText(getApplicationContext(),"Degustación creada",Toast.LENGTH_LONG).show();
                            Degustacion degustacion = new Degustacion(num++, u.getUsername(), idPlato, (int)(calificacion.getRating())+"");
                            c.crearDegustacion(degustacion);
                            Intent intent = new Intent(NuevaDegustacionActivity.this, MainActivity2.class);
                            startActivity(intent);
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Error al crear la degustación",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}