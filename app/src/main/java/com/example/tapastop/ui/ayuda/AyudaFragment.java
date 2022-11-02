package com.example.tapastop.ui.ayuda;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tapastop.LoginActivity;
import com.example.tapastop.R;
import com.example.tapastop.crearCuenta;
import com.example.tapastop.databinding.FragmentAyudaBinding;

public class AyudaFragment extends Fragment implements View.OnClickListener{

    private FragmentAyudaBinding binding;

    Button ajustes;
    Button ayuda;

    View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AyudaViewModel ayudaViewModel =
                new ViewModelProvider(this).get(AyudaViewModel.class);

        binding = FragmentAyudaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textAyuda;
        //ayudaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {

    }
}