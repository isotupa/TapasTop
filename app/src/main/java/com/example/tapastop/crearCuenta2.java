package com.example.tapastop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.tapastop.Entidades.Usuario;
import com.example.tapastop.backend.sqlte.Controlador;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class crearCuenta2 extends AppCompatActivity {

    Button volver;
    Button terminar;

    EditText nombre;
    EditText ap1;
    EditText ap2;
    EditText ubi;
    EditText info;

    ImageButton img;
    byte[] sqlimg;

    public static final int GET_FROM_GALLERY = 3;

    Controlador c;
    Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta2);

        nombre =(EditText) findViewById(R.id.nombreCCTxtEdit);
        ap1 =(EditText) findViewById(R.id.apellido1CCTxtxEdit);
        ap2 =(EditText) findViewById(R.id.apellido2CCTxtEdit);
        ubi =(EditText) findViewById(R.id.ubiCCTxtEdit);
        info =(EditText) findViewById(R.id.bioCCTxtEdit);

        c = new Controlador(this.findViewById(android.R.id.content).getRootView().getContext());
        u = c.getUsuario(getIntent().getExtras().get("username").toString());

        img = findViewById(R.id.fotoPerfilCCImgBtn);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            }
        });


        volver =(Button)findViewById(R.id.volverCC2Btn);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(crearCuenta2.this, crearCuenta.class);
                startActivity(intent);
            }
        });

        terminar =(Button)findViewById(R.id.terminarCC2Btn);
        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Usuario u = c.activo;
                u.setNombre(String.valueOf(nombre.getText()));
                u.setAp1(String.valueOf(ap1.getText()));
                u.setAp2(String.valueOf(ap2.getText()));
                u.setUbi(String.valueOf(ubi.getText()));
                u.setBio(String.valueOf(info.getText()));
                c.crearCuenta2(u);
                Intent intent = new Intent(crearCuenta2.this, VueltaALogin.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == crearCuenta2.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                img.setImageURI(selectedImage);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                u.setFoto(byteArray);
                c.crearCuenta2(u);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}