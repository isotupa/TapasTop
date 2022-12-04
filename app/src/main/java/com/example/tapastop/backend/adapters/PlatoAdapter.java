package com.example.tapastop.backend.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapastop.Entidades.Plato_comida;
import com.example.tapastop.Entidades.Restaurante;
import com.example.tapastop.R;

import java.util.List;

public class PlatoAdapter extends RecyclerView.Adapter<PlatoAdapter.ViewHolder>{
    List<Plato_comida> platos;

    // Constructor for initialization
    public PlatoAdapter(List<Plato_comida> platos) {
        this.platos = platos;
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
        holder.text.setText(platos.get(position).getNombre()+"");
        //holder.dir.setText(platos.get(position).getRestaurante()+"");
    }

    @Override
    public int getItemCount() {
        return platos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        //TextView dir;

        public ViewHolder(View view) {
            super(view);
            text = itemView.findViewById(R.id.plato);
            //dir = itemView.findViewById(R.id.plato2);
        }
    }
}
