package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tapastop.backend.sqlte.Controlador;

public class crearCuenta extends AppCompatActivity {

    Button volver;
    Button siguiente;
    EditText username ;
    EditText password ;
    EditText email ;
    EditText edad ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        username = (EditText)findViewById(R.id.usernameCC1TxtEdit);
        password = (EditText)findViewById(R.id.pw1CC1TxtEdit);
        email = (EditText)findViewById(R.id.emailCC1TxtEdit);

        volver =(Button)findViewById(R.id.volverCC1btn);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(crearCuenta.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        siguiente =(Button)findViewById(R.id.sigCC1Btn);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(crearCuenta.this, crearCuenta2.class);
                startActivity(intent);
            }
        });

    }
}