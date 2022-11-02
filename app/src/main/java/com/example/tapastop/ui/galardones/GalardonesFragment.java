package com.example.tapastop.ui.galardones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tapastop.databinding.FragmentAyudaBinding;
import com.example.tapastop.databinding.FragmentGalardonesBinding;

public class GalardonesFragment extends Fragment {

    private FragmentGalardonesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalardonesViewModel galardonesViewModel =
                new ViewModelProvider(this).get(GalardonesViewModel.class);

        binding = FragmentGalardonesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGalardones;
        galardonesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}