package com.example.tapastop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tapastop.backend.sqlte.Controlador;
import com.example.tapastop.ui.ayuda.AyudaFragment;

public class EditarPerfilActivity extends AppCompatActivity {

    Button cancelar;
    Button guardar;
    Button eliminar;

    EditText nombre;
    EditText ap1, ap2, username, email, ubi, genero, bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        Controlador c = new Controlador(this.findViewById(android.R.id.content).getRootView().getContext());

        nombre = findViewById(R.id.nombreEPEdit);
        ap1 = findViewById(R.id.ap1EPEdit);
        ap2 = findViewById(R.id.ap2EPEdit);
        username = findViewById(R.id.cambiarUsernameEPBtn);
        email = findViewById(R.id.emailEPEdit);
        ubi = findViewById(R.id.ubiEPEdit);
        genero = findViewById(R.id.sexoEPEdit);
        bio = findViewById(R.id.bioEPEdit);

        nombre.setText(c.activo.getNombre());
        ap1.setText(c.activo.getAp1());
        ap2.setText(c.activo.getAp2());
        username.setText(c.activo.getUsername());
        email.setText(c.activo.getEmail());
        ubi.setText(c.activo.getUbi());
        //genero.setText(c.activo.get());
        bio.setText(c.activo.getBio());


        cancelar =(Button)findViewById(R.id.cancelarEPBtn);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarPerfilActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        guardar =(Button)findViewById(R.id.guardarEPBtn);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.activo.setNombre(nombre.getText().toString());
                c.activo.setAp1(ap1.getText().toString());
                c.activo.setAp2(ap2.getText().toString());
                c.activo.setUsername(username.getText().toString());
                c.activo.setEmail(email.getText().toString());
                c.activo.setUbi(ubi.getText().toString());
                c.activo.setBio(bio.getText().toString());

                //c.crearCuenta2(c.activo);

                //c.activo = c.getUsuario(username.getText().toString());

                Intent intent = new Intent(EditarPerfilActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        eliminar =(Button)findViewById(R.id.borrarPerfilEPBtn);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditarPerfilActivity.this);
                builder.setTitle(R.string.app_name);
                builder.setIcon(R.drawable.iconmonstr_error_filled);
                builder.setMessage("¿Estás seguro de querer borrar el perfil?")
                        .setCancelable(false)
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                c.borrar_usuario(c.activo);
                                Toast.makeText(getApplicationContext(), "Cuenta borrada",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(EditarPerfilActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }
}