package com.example.tapastop.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapastop.Entidades.Degustacion;
import com.example.tapastop.NuevaDegustacionActivity;
import com.example.tapastop.R;
import com.example.tapastop.backend.adapters.DegustacionAdapter;
import com.example.tapastop.backend.sqlte.Controlador;
import com.example.tapastop.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    private Button nuevaDegustacion;

    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recyclerView);

        Controlador c = new Controlador(getContext());

        // layout for vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        // Sending reference and data to DegustacionAdapter
        List<Degustacion> listaDegustaciones = new ArrayList<>();
        listaDegustaciones.add(new Degustacion(1, c.activo.getUsername(), 1, "5"));
        listaDegustaciones.add(new Degustacion(2, c.activo.getUsername(), 2, "4"));
        listaDegustaciones.add(new Degustacion(3, c.activo.getUsername(), 3, "1"));
        listaDegustaciones.add(new Degustacion(4, c.activo.getUsername(), 4, "2"));
        listaDegustaciones.add(new Degustacion(5, c.activo.getUsername(), 5, "3"));
        listaDegustaciones.add(new Degustacion(6, c.activo.getUsername(), 6, "4"));
        //DegustacionAdapter adapter = new DegustacionAdapter(listaDegustaciones);
        DegustacionAdapter adapter = new DegustacionAdapter(c.listarDegustaciones(c.getUsuario(c.getUser_a()).getUsername()));

        // Setting DegustacionAdapter to RecyclerView
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        nuevaDegustacion = root.findViewById(R.id.nuevaDegustacionNotBtn);
        nuevaDegustacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NuevaDegustacionActivity.class);
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


