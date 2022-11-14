package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tapastop.ui.notifications.NotificationsFragment;

public class NuevaDegustacionActivity extends AppCompatActivity {

    Button cancelar;
    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_degustacion);

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
                Toast.makeText(getApplicationContext(),"Degustación guardada",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(NuevaDegustacionActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}