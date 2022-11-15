package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tapastop.Entidades.Usuario;
import com.example.tapastop.backend.sqlte.Controlador;

public class LoginActivity extends AppCompatActivity {

    Button forgotPw;
    Button login;
    Button crearCuenta;
    EditText email;
    EditText pw;

    /*
    - Añadir imágenes a la entidad Restaurante
    - Añadir botones en home
    - Mirar porqué no se guarda el nombre real
    - Clases para conseguir últimas degustaciones de un usuario
    - Añadir galardones
    - Búsqueda de restaurantes funcional
    - Botón de ayuda que funcione
    - Activación cuenta/ mandar email
    - Añadir google maps para definir ubicación de restaurante
    - Modificar degustación
    - Búsqueda de degustaciones?
    - Conseguir niveles de galardones
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Controlador c = new Controlador(this.findViewById(android.R.id.content).getRootView().getContext());

        email = (EditText)findViewById(R.id.emailLoginTxtEdit);
        pw = (EditText)findViewById(R.id.pwLoginTextEdit);

        login =(Button)findViewById(R.id.loginLoginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(String.valueOf(email.getText()).isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Pon un nombre",Toast.LENGTH_LONG).show();
                } else if(String.valueOf(pw.getText()).isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Pon una contraseña",Toast.LENGTH_LONG).show();
                } else if(!c.login(String.valueOf(email.getText()),String.valueOf(pw.getText()))) {
                    Toast.makeText(getApplicationContext(), "E-mail o contraseña incorrectos",Toast.LENGTH_LONG).show();
                } else {
                    c.activo = c.getUsuario(String.valueOf(email.getText()));
                    Intent intent = new Intent(LoginActivity.this, MainActivity2.class);
                    startActivity(intent);
                }
            }
        });

        forgotPw =(Button)findViewById(R.id.forgotPwLoginBtn);
        forgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPw.class);
                startActivity(intent);
            }
        });

        crearCuenta =(Button)findViewById(R.id.crearCuentaLoginBtn);
        crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, crearCuenta.class);
                startActivity(intent);
            }
        });
    }
}