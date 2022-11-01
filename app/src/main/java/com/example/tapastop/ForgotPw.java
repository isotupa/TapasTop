package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPw extends AppCompatActivity {

    Button volver;
    Button confirmar;

    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pw);

        email = (EditText)findViewById(R.id.emailRCBtn);

        volver =(Button)findViewById(R.id.volverRCBtn);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPw.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        confirmar = (Button)findViewById(R.id.confirmarRCBtn);
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Mira el correo de " + email.getText(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ForgotPw.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
/*
    @Override
    protected void onDestroy() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        super.onDestroy();
    }*/
}