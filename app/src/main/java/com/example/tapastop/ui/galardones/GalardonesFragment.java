package com.example.tapastop.ui.galardones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapastop.Entidades.Degustacion;
import com.example.tapastop.Entidades.Galardones;
import com.example.tapastop.Entidades.Usuario;
import com.example.tapastop.FavFoodActivity;
import com.example.tapastop.R;
import com.example.tapastop.backend.adapters.DegustacionAdapter;
import com.example.tapastop.backend.adapters.PlatoAdapter;
import com.example.tapastop.backend.sqlte.Controlador;
import com.example.tapastop.databinding.FragmentAyudaBinding;
import com.example.tapastop.databinding.FragmentGalardonesBinding;

import java.util.ArrayList;
import java.util.List;

public class GalardonesFragment extends Fragment {

    private FragmentGalardonesBinding binding;

    RecyclerView rec;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentGalardonesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rec = root.findViewById(R.id.recc);

        Controlador c = new Controlador(getContext());
        Usuario u = c.getUsuario(c.getUser_a());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rec.setLayoutManager(linearLayoutManager);

        //Galardones g = new Galardones();
        List<String> gs = new ArrayList<>();
        gs.add("Paellas");
        gs.add("Pizzas");
        gs.add("Hamburguesas");
        PlatoAdapter adapter = new PlatoAdapter(gs);

        rec.setAdapter(adapter);
        rec.setItemAnimator(new DefaultItemAnimator());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}