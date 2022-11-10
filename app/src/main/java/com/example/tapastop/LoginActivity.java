package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tapastop.backend.sqlte.Controlador;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    Button forgotPw;
    Button login;
    Button crearCuenta;
    //Controlador c = new Controlador();
    EditText email;
    EditText pw;

    TextView aviso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        aviso = (TextView)findViewById(R.id.avisoLoginTxt);
        aviso.setVisibility(View.INVISIBLE);

        email = (EditText)findViewById(R.id.emailLoginTxtEdit);

        login =(Button)findViewById(R.id.loginLoginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().equals("a")) {
                    aviso.setVisibility(View.VISIBLE);
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