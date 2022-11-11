package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tapastop.Entidades.Usuario;
import com.example.tapastop.backend.sqlte.Controlador;

public class crearCuenta extends AppCompatActivity {

    Button volver;
    Button siguiente;
    EditText username;
    EditText password1;
    EditText password2;
    EditText email;
    EditText edad;

    TextView edadWarning;
    TextView pwWarning;
    TextView emailWarning;
    TextView usernameWarning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        edadWarning = (TextView)findViewById(R.id.edadWarning);
        pwWarning = (TextView)findViewById(R.id.pwWarning);
        emailWarning = (TextView)findViewById(R.id.emailWarning);
        usernameWarning = (TextView)findViewById(R.id.usernameWarning);

        //edadWarning.setVisibility(View.INVISIBLE);
        pwWarning.setVisibility(View.INVISIBLE);
        emailWarning.setVisibility(View.INVISIBLE);
        usernameWarning.setVisibility(View.INVISIBLE);

        username = (EditText)findViewById(R.id.usernameCC1TxtEdit);
        password1 = (EditText)findViewById(R.id.pw1CC1TxtEdit);
        password2 = (EditText)findViewById(R.id.pw2CC1TxtEdit);
        email = (EditText)findViewById(R.id.emailCC1TxtEdit);
        edad = (EditText)findViewById(R.id.edadCC1NumberEdit);

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
                if(String.valueOf(username.getText()).equals("")) {

                }
                else if(Integer.parseInt(String.valueOf(edad.getText())) < 18) {
                    edadWarning.setVisibility(View.VISIBLE);
                }
                else if(!String.valueOf(password2.getText()).equals(String.valueOf(password1.getText()))) {
                    pwWarning.setVisibility(View.VISIBLE);
                }
                else if(String.valueOf(email.getText()).equals("")) {

                }
                else {
                    Usuario u = new Usuario(String.valueOf(username.getText()), String.valueOf(password2.getText()),
                            String.valueOf(edad.getText()), String.valueOf(email.getText()));
                    //c.crearCuenta(u);
                    Intent intent = new Intent(crearCuenta.this, crearCuenta2.class);
                    startActivity(intent);
                }

            }
        });

    }
}