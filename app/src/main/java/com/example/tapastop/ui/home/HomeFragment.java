package com.example.tapastop.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tapastop.EditarPerfilActivity;
import com.example.tapastop.Entidades.Degustacion;
import com.example.tapastop.Entidades.Plato_comida;
import com.example.tapastop.Entidades.Usuario;
import com.example.tapastop.FavFoodActivity;
import com.example.tapastop.GalardonesActivity;
import com.example.tapastop.MainActivity2;
import com.example.tapastop.R;
import com.example.tapastop.UserActivity;
import com.example.tapastop.backend.sqlte.Controlador;
import com.example.tapastop.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    TextView nombre;
    TextView username;
    TextView degust;
    TextView locales_nuevos;
    TextView solicitudes;

    TextView comFavorita1;
    TextView comFavorita2;
    TextView comFavorita3;

    TextView galardon1;
    TextView galardon2;
    TextView galardon3;

    ImageView foto;

    Button user;
    Button favourite;
    Button galardones;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        nombre = root.findViewById(R.id.nobreUsuarioTxtEdit);
        username = root.findViewById(R.id.nombreFH);
        degust = root.findViewById(R.id.nDegustFH);
        locales_nuevos = root.findViewById(R.id.locNuevosFH);
        solicitudes = root.findViewById(R.id.solicitudesFH);

        comFavorita1 = root.findViewById(R.id.comFavorita1);
        comFavorita2 = root.findViewById(R.id.comFavorita2);
        comFavorita3 = root.findViewById(R.id.comFavorita3);

        galardon1 = root.findViewById(R.id.galardon1);
        galardon2 = root.findViewById(R.id.galardon2);
        galardon3 = root.findViewById(R.id.galardon3);

        foto = root.findViewById(R.id.fotoFH);

        user = root.findViewById(R.id.userBtn);
        favourite = root.findViewById(R.id.favFoodBtn);
        galardones = root.findViewById(R.id.galardonesBtn);

        Controlador c = new Controlador(getActivity().getApplicationContext());
        Usuario u = c.getUsuario(c.getUser_a());

        if(u.getNombre() != null)
            nombre.setText(u.getNombre());
        username.setText(u.getUsername());
        if(c.listarDegustaciones(u.getUsername()) != null)
            degust.setText("Degustaciones: " + c.listarDegustaciones(u.getUsername()).size());
        locales_nuevos.setText("Locales nuevos: " + c.restaurantes_usuarios(u.getUsername()).size());
        solicitudes.setText("0 solicitudes nuevas");

        List<Degustacion> listFavoritas = c.listar_degustaciones_Orden_calificacion(u.getUsername());

        if(listFavoritas.size() > 0) comFavorita1.setText(c.get_plato_comida(listFavoritas.get(0).getId_plato()));
        else comFavorita1.setText("¡No has degustado suficientes platos!");
        if(listFavoritas.size() > 1) comFavorita2.setText(c.get_plato_comida(listFavoritas.get(1).getId_plato()));
        else comFavorita2.setText("¡No has degustado suficientes platos!");
        if(listFavoritas.size() > 2) comFavorita3.setText(c.get_plato_comida(listFavoritas.get(2).getId_plato()));
        else comFavorita3.setText("¡No has degustado suficientes platos!");

        if(u.getFoto() != null) {
            byte[] blob = u.getFoto();
            Bitmap bmp= BitmapFactory.decodeByteArray(blob,0,blob.length);
            foto.setImageBitmap(bmp);
        } else {
            foto.setImageResource(R.drawable.iconmonstr_error_filled);
        }

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UserActivity.class);
                startActivity(intent);
            }
        });
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FavFoodActivity.class);
                startActivity(intent);
            }
        });
        galardones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), GalardonesActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}