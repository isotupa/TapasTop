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

import com.example.tapastop.Entidades.Plato_comida;
import com.example.tapastop.backend.sqlte.Controlador;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class NuevoPlatoActivity extends AppCompatActivity {

    EditText nombre;
    EditText region;
    EditText sabor;
    EditText tipo;

    ImageButton foto;

    Button guardar;
    Button cancelar;

    Plato_comida plato;

    byte[] byteArray;

    public static final int GET_FROM_GALLERY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_plato);

        nombre = findViewById(R.id.direccionNRTxtEdit2);
        region = findViewById(R.id.regionComida);
        sabor = findViewById(R.id.saborComida);
        tipo = findViewById(R.id.tipoComida);
        foto = findViewById(R.id.fotoPlatoNuevo);

        guardar = findViewById(R.id.button2);
        cancelar = findViewById(R.id.button3);

        String nameRestaurant = getIntent().getExtras().get("restaurantName") + "";

        Controlador c = new Controlador(this.findViewById(android.R.id.content).getRootView().getContext());

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int number = random.nextInt(100000);
                //int id = c.getIdPlato(getIntent().getExtras().get("restaurantName")+"", nombre.getText().toString());
                plato = new Plato_comida(number, nombre.getText().toString(), tipo.getText().toString(),
                        region.getText().toString(), sabor.getText().toString(), getIntent().getExtras().get("restaurantName")+"");
                c.crearPlatoComida(plato);
                Intent intent = new Intent(NuevoPlatoActivity.this, RestaurantScreenActivity.class);
                intent.putExtra("restaurantName", getIntent().getExtras().get("restaurantName").toString());
                intent.putExtra("restaurantAddress", getIntent().getExtras().get("restaurantAddress") +"");
                startActivity(intent);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoPlatoActivity.this, RestaurantScreenActivity.class);
                intent.putExtra("restaurantName", getIntent().getExtras().get("restaurantName").toString());
                intent.putExtra("restaurantAddress", getIntent().getExtras().get("restaurantAddress") +"");
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
                foto.setImageURI(selectedImage);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();
                //u.setFoto(byteArray);
                //c.crearCuenta2(u);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}