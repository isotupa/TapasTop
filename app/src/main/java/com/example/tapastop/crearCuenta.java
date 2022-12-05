package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    Usuario usuario ;
    TextView edadWarning;
    TextView pwWarning;
    TextView emailWarning;
    TextView usernameWarning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        //edadWarning = (TextView)findViewById(R.id.editTextDate);
        pwWarning = (TextView)findViewById(R.id.pwWarning);
        emailWarning = (TextView)findViewById(R.id.emailWarning);
        usernameWarning = (TextView)findViewById(R.id.usernameWarning);

        Controlador c = new Controlador(this.findViewById(android.R.id.content).getRootView().getContext());

        //edadWarning.setVisibility(View.INVISIBLE);
        pwWarning.setVisibility(View.INVISIBLE);
        emailWarning.setVisibility(View.INVISIBLE);
        usernameWarning.setVisibility(View.INVISIBLE);

        username = (EditText)findViewById(R.id.usernameCC1TxtEdit);
        password1 = (EditText)findViewById(R.id.pw1CC1TxtEdit);
        password2 = (EditText)findViewById(R.id.pw2CC1TxtEdit);
        email = (EditText)findViewById(R.id.emailCC1TxtEdit);
        edad = (EditText)findViewById(R.id.editTextDate);

        usuario = new Usuario(username.getText().toString(),password1.getText().toString(),
                edad.getText().toString(),email.getText().toString());

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

                String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
                String DATE_REGEX = "^[0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{4}$";

                int anyos = 0;

                if(String.valueOf(username.getText()).isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Pon un nombre válido",Toast.LENGTH_LONG).show();
                } else if(c.getUsuario(username.getText().toString()) != null) {
                    Toast.makeText(getApplicationContext(), "¡Este nombre de cuenta ya existe!",Toast.LENGTH_LONG).show();
                } else if(!String.valueOf(password1.getText()).equals(String.valueOf(password2.getText()))) {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden",Toast.LENGTH_LONG).show();
                } else if(!String.valueOf(email.getText()).matches(EMAIL_REGEX)) {
                    Toast.makeText(getApplicationContext(), "El formato del email no es correcto",Toast.LENGTH_LONG).show();
                } else if(!String.valueOf(edad.getText()).matches(DATE_REGEX)) {
                    Toast.makeText(getApplicationContext(), "Introduce una fecha válida",Toast.LENGTH_LONG).show();
                } else if(String.valueOf(edad.getText()).matches(DATE_REGEX)) {
                    String[] res = edad.getText().toString().split("/");
                    int dia = Integer.parseInt(res[0]);
                    int mes = Integer.parseInt(res[1]);
                    int ano = Integer.parseInt(res[2]);
                    if((dia < 0 || dia > 31) || (mes < 0 || mes > 12) || (ano < 1900 || ano > 2022)) {
                        Toast.makeText(getApplicationContext(), "Introduce una fecha válida",Toast.LENGTH_LONG).show();
                    } else if (ano > 2004) {
                        Toast.makeText(getApplicationContext(), "Debes tener por lo menos 18 años",Toast.LENGTH_LONG).show();
                    } else {
                        anyos = 2022 - ano;
                        Usuario u = new Usuario(String.valueOf(username.getText()), String.valueOf(password2.getText()),
                                anyos + "", String.valueOf(email.getText()));
                        c.crearCuenta(u);
                        c.activo = u;
                        //startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + String.valueOf(email.getText()))));
                        Intent intent = new Intent(crearCuenta.this, crearCuenta2.class);
                        intent.putExtra("username", username.getText().toString());
                        startActivity(intent);
                    }
                } else {
                    Usuario u = new Usuario(String.valueOf(username.getText()), String.valueOf(password2.getText()),
                            anyos + "", String.valueOf(email.getText()));
                    c.crearCuenta(u);
                    c.activo = u;
                    //startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + String.valueOf(email.getText()))));
                    Intent intent = new Intent(crearCuenta.this, crearCuenta2.class);
                    intent.putExtra("username", username.getText().toString());
                    startActivity(intent);
                }

            }
        });

    }
}