package com.example.tapastop.ui.ayuda;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.tapastop.EditarPerfilActivity;
import com.example.tapastop.LoginActivity;
import com.example.tapastop.R;
import com.example.tapastop.crearCuenta;
import com.example.tapastop.databinding.FragmentAyudaBinding;

import java.util.logging.Logger;

public class AyudaFragment extends Fragment {

    private FragmentAyudaBinding binding;

    Button ajustes;
    Button ayuda;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //AyudaViewModel ayudaViewModel =
                //new ViewModelProvider(this).get(AyudaViewModel.class);

        binding = FragmentAyudaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ajustes =(Button)root.findViewById(R.id.ajustesAyudaBtn);
        ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditarPerfilActivity.class);
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