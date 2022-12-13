package com.example.tapastop.backend.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapastop.R;

import java.util.List;

public class PlatoAdapter extends RecyclerView.Adapter<PlatoAdapter.ViewHolder>{
    List<String> deg;

    // Constructor for initialization
    public PlatoAdapter(List<String> deg) {
        this.deg = deg;
    }
    @NonNull
    @Override
    public PlatoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item_plato, parent, false);

        // Passing view to ViewHolder
        PlatoAdapter.ViewHolder viewHolder = new PlatoAdapter.ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull PlatoAdapter.ViewHolder holder, int position) {
        holder.gal.setText(deg.get(position)+"");
        holder.niv.setText(deg.get(position)+"");
    }

    @Override
    public int getItemCount() {
        return deg.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView gal;
        TextView niv;

        public ViewHolder(View view) {
            super(view);
            gal = itemView.findViewById(R.id.galardon);
            niv = itemView.findViewById(R.id.nivel);
        }
    }
}
