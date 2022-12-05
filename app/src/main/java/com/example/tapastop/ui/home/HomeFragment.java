package com.example.tapastop.ui.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tapastop.Entidades.Usuario;
import com.example.tapastop.R;
import com.example.tapastop.backend.sqlte.Controlador;
import com.example.tapastop.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    TextView nombre;
    TextView username;
    TextView degust;
    TextView locales_nuevos;
    TextView solicitudes;
    ImageView foto;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        nombre = root.findViewById(R.id.nobreUsuarioTxtEdit);
        username = root.findViewById(R.id.nombreFH);
        degust = root.findViewById(R.id.nDegustFH);
        locales_nuevos = root.findViewById(R.id.locNuevosFH);
        solicitudes = root.findViewById(R.id.solicitudesFH);
        foto = root.findViewById(R.id.fotoFH);

        Controlador c = new Controlador(getActivity().getApplicationContext());
        Usuario u = c.activo;

        nombre.setText(u.getNombre());
        username.setText(u.getUsername());
        degust.setText("Sin información");
        locales_nuevos.setText("Sin información");
        solicitudes.setText("Sin información");
        if(u.getFoto() != null) {
            byte[] blob = u.getFoto();
            Bitmap bmp= BitmapFactory.decodeByteArray(blob,0,blob.length);
            foto.setImageBitmap(bmp);
        } else {
            foto.setImageResource(R.drawable.iconmonstr_error_filled);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}