package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tapastop.backend.sqlte.GMail;
import com.example.tapastop.backend.sqlte.SendMailTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                Toast.makeText(getApplicationContext(),"Mira el correo de " + email.getText(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ForgotPw.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void sendEmail() {
        Log.i("SendMailActivity", "Send Button Clicked.");

        String fromEmail = "tapastopnoreply@gmail.com";
        String fromPassword = "tapastop13";
        String toEmails = "marko.isotupa01@gmail.com";
        //List toEmailList = Arrays.asList(toEmails.split("\\s*,\\s*"));
        List toEmailList = new ArrayList();
        toEmailList.add(toEmails);
        Log.i("SendMailActivity", "To List: " + toEmailList);
        String emailSubject = "prueba";
        String emailBody = "funciona";
        try {
            new SendMailTask(ForgotPw.this).execute(fromEmail, fromPassword, toEmailList, emailSubject, emailBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*try {
            GMail sender = new GMail("tapastopnoreply@gmail.com", "tapastop13");
            sender.sendMail("This is Subject",
                    "This is Body",
                    "tapastopnoreply@gmail.com",
                    "kaizalk96@gmail.com");
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }*/
    }

}