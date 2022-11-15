package com.example.tapastop.backend.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapastop.Entidades.Degustacion;
import com.example.tapastop.R;

import java.util.ArrayList;
import java.util.List;

public class DegustacionAdapter extends RecyclerView.Adapter<DegustacionAdapter.ViewHolder>{
    List<Degustacion> degustaciones;
    //Context context;

    // Constructor for initialization
    public DegustacionAdapter(List<Degustacion> degustaciones) {
        this.degustaciones = degustaciones;
    }
    @NonNull
    @Override
    public DegustacionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item, parent, false);

        // Passing view to ViewHolder
        DegustacionAdapter.ViewHolder viewHolder = new DegustacionAdapter.ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull DegustacionAdapter.ViewHolder holder, int position) {
        //int res = (int) degustaciones.get(position).get;
        //holder.images.setImageResource(res);
        holder.plato.setText(degustaciones.get(position).getId_plato()+"");
        //holder.restaurante.setText("un restaurante");
    }
    @Override
    public int getItemCount() {
        return degustaciones.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;
        TextView plato;
        TextView restaurante;

        public ViewHolder(View view) {
            super(view);
            images = itemView.findViewById(R.id.image);
            plato = itemView.findViewById(R.id.plato);
            restaurante = itemView.findViewById(R.id.restaurante);
        }
    }
}
