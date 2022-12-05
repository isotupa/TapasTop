package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
                sendEmail();
                //Toast.makeText(getApplicationContext(),"Mira el correo de " + email.getText(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ForgotPw.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void sendEmail() {
        Log.i("Send email", "");

        String[] TO = {"kaizalk96@gmail.com"};
        //String[] CC = {"kaizalk96@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ForgotPw.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
/*
    @Override
    protected void onDestroy() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        super.onDestroy();
    }*/
}