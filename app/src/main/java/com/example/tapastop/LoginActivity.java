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

import com.example.tapastop.Entidades.Galardones;
import com.example.tapastop.Entidades.Plato_comida;
import com.example.tapastop.Entidades.Restaurante;
import com.example.tapastop.Entidades.Usuario;
import com.example.tapastop.backend.sqlte.Controlador;
import com.example.tapastop.backend.sqlte.Modelo;

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
    - Sacar número de plato a partir de ID
    - Sacar nombre de restaurante a partir de ID de plato
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Controlador c = new Controlador(this.findViewById(android.R.id.content).getRootView().getContext());

        email = (EditText) findViewById(R.id.emailLoginTxtEdit);
        pw = (EditText) findViewById(R.id.pwLoginTextEdit);

        login = (Button) findViewById(R.id.loginLoginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(email.getText()).isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Pon un nombre", Toast.LENGTH_LONG).show();
                } else if (String.valueOf(pw.getText()).isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Pon una contraseña", Toast.LENGTH_LONG).show();
                } else if (!c.login(String.valueOf(email.getText()), String.valueOf(pw.getText()))) {
                    Toast.makeText(getApplicationContext(), "E-mail o contraseña incorrectos", Toast.LENGTH_LONG).show();
                } else {
                    //c.activo = c.getUsuario(String.valueOf(email.getText()));
                    c.getActivo().setUsername(email.getText().toString());
                    //c.getActivo(set)
                    //c.setActivo(c.getUsuario(email.getText().toString()));
                    c.setUser_a(email.getText().toString());
                    //System.out.println("ESTE ES MI LOGIN2" +c.getActivo().getUsername());
                    Intent intent = new Intent(LoginActivity.this, MainActivity2.class);
                    startActivity(intent);
                }
            }
        });

        forgotPw = (Button) findViewById(R.id.forgotPwLoginBtn);
        forgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotPw.class);
                startActivity(intent);
            }
        });

        crearCuenta = (Button) findViewById(R.id.crearCuentaLoginBtn);
        crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, crearCuenta.class);
                startActivity(intent);
            }
        });

        if(c.restaurantes_usuarios("admin").size() == 0) {
            Usuario admin = new Usuario("admin","admin","21","admin@admin.com");
            c.crearCuenta(admin);
            Restaurante res1 = new Restaurante("MacDonalds", "Calle Mayor", "admin");
            Restaurante res2 = new Restaurante("Kurger Bing", "Calle Menor", "admin");
            Restaurante res3 = new Restaurante("Wendys", "Calle Central", "admin");

            c.crearRestaurante(res1);
            c.crearRestaurante(res2);
            c.crearRestaurante(res3);

            Plato_comida p1 = new Plato_comida(10, "KrangreBurger", "Comida Rápida", "USA", "Malo", "MacDonalds");
            Plato_comida p2 = new Plato_comida(11, "QuarterPound", "Comida Rápida", "USA", "Bien", "Kurger Bing");
            Plato_comida p3 = new Plato_comida(12, "Burger", "Comida Rápida", "USA", "Malo", "Wendys");

            c.crearPlatoComida(p1);
            c.crearPlatoComida(p2);
            c.crearPlatoComida(p3);

            Galardones g1 = new Galardones("admin", "Paella", 1, 0, 0);
            Galardones g2 = new Galardones("admin", "Pizza", 2, 0, 0);
            Galardones g3 = new Galardones("admin", "Tortilla", 3, 0, 0);

            Modelo.nuevo_galardon(g1);
            Modelo.nuevo_galardon(g2);
            Modelo.nuevo_galardon(g3);

        }



        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        String appLinkAction = intent.getAction();
        Uri appLinkData = intent.getData();
        Toast.makeText(getApplicationContext(), "Usuario verificado!", Toast.LENGTH_LONG).show();
    }
}