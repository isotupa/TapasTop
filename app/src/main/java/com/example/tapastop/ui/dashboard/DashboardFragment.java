package com.example.tapastop.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapastop.R;
import com.example.tapastop.backend.sqlte.Controlador;
import com.example.tapastop.backend.adapters.RestauranteAdapter;
import com.example.tapastop.databinding.FragmentDashboardBinding;
import com.example.tapastop.nuevoRestauranteActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    SearchView sd;
    Button nuevo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        RecyclerView recyclerView;
        ArrayList Img = new ArrayList<>(Arrays.asList(R.drawable.burger_king_410x282, R.drawable.iconmonstr_question_thin,
                R.drawable.iconmonstr_question_thin, R.drawable.el_jardin_de_la_maquina_44_1_81582,
                R.drawable.iconmonstr_question_thin, R.drawable.iconmonstr_question_thin, R.drawable.iconmonstr_question_thin
                , R.drawable.iconmonstr_question_thin, R.drawable.iconmonstr_question_thin));
        ArrayList Name = new ArrayList<>(Arrays.asList("Burger King", "La tagliatella", "VIPS",
                "El Jardín de la máquina", "Tierra", "Cosa", "McDonalds", "Zen", "Forrest"));

        Controlador c = new Controlador(getContext());

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sd = root.findViewById(R.id.searchDash);

        recyclerView = root.findViewById(R.id.recyclerView);

        // layout for vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        // Sending reference and data to DegustacionAdapter
        RestauranteAdapter adapter = new RestauranteAdapter(c.listarRestaurantes(""));

        // Setting DegustacionAdapter to RecyclerView
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        nuevo = root.findViewById(R.id.nuevoRestaurante);
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), nuevoRestauranteActivity.class);
                startActivity(intent);
            }
        });


        //sd.getQuery()

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}