package com.example.tapastop.backend.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapastop.Entidades.Degustacion;
import com.example.tapastop.R;
import com.example.tapastop.backend.sqlte.Modelo;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item_degustaciones, parent, false);

        // Passing view to ViewHolder
        DegustacionAdapter.ViewHolder viewHolder = new DegustacionAdapter.ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull DegustacionAdapter.ViewHolder holder, int position) {

        holder.plato.setText(Modelo.get_plato_comida(degustaciones.get(position).getId_plato()));
        holder.rating.setRating(Integer.parseInt(degustaciones.get(position).getCalificacion()));
        //holder.rating.setRating((float)3.5);
        System.out.println(degustaciones.get(position).getId_plato());
        holder.restaurante.setText(Modelo.get_Restaurante(degustaciones.get(position).getId_plato()).getNombre());
    }

    @Override
    public int getItemCount() {
        return degustaciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView plato;
        TextView restaurante;

        RatingBar rating;

        public ViewHolder(View view) {
            super(view);
            plato = itemView.findViewById(R.id.galardon);
            restaurante = itemView.findViewById(R.id.restaurante);
            rating = (RatingBar) itemView.findViewById(R.id.ratingBar);
            rating.setIsIndicator(true);
        }
    }
}
