package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tapastop.backend.sqlte.Controlador;

public class LoginActivity extends AppCompatActivity {

    Button forgotPw;
    Button login;
    Button crearCuenta;
    EditText email;
    EditText pw;

    TextView aviso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Controlador c = new Controlador(this.findViewById(android.R.id.content).getRootView().getContext());

        aviso = (TextView)findViewById(R.id.avisoLoginTxt);
        aviso.setVisibility(View.INVISIBLE);

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
                    aviso.setVisibility(View.INVISIBLE);
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