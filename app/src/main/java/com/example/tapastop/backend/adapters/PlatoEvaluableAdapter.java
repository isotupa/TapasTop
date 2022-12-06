package com.example.tapastop.backend.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapastop.Entidades.Degustacion;
import com.example.tapastop.Entidades.Plato_comida;
import com.example.tapastop.R;
import com.example.tapastop.backend.sqlte.Controlador;
import com.example.tapastop.backend.sqlte.Modelo;

import java.util.List;
import java.util.Random;

public class PlatoEvaluableAdapter extends RecyclerView.Adapter<PlatoEvaluableAdapter.ViewHolder>{
    List<Plato_comida> platos;
    //Context context;

    // Constructor for initialization
    public PlatoEvaluableAdapter(List<Plato_comida> platos) {
        this.platos = platos;
    }
    @NonNull
    @Override
    public PlatoEvaluableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item_platos_evaluables, parent, false);

        // Passing view to ViewHolder
        PlatoEvaluableAdapter.ViewHolder viewHolder = new PlatoEvaluableAdapter.ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull PlatoEvaluableAdapter.ViewHolder holder, int position) {
        holder.plato.setText(platos.get(position).getNombre());
        //holder.restaurante.setText(Modelo.get_Restaurante(degustaciones.get(position).getId_plato()).getNombre());
        List<Degustacion> l = Modelo.listar_degustaciones_restaurante(platos.get(position).getId());
        double avg = 0;
        for(int i = 0; i < l.size(); i++) {
            avg += Integer.parseInt(l.get(i).getCalificacion());
        }
        if(l.size() > 0) avg = avg/l.size();
        else avg = 2.5;
        holder.rating.setRating((float) avg);
        //holder.rating.setRating((float)3.5);
        //holder.restaurante.setText("un restaurante");
    }

    @Override
    public int getItemCount() {
        return platos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView plato;
        RatingBar rating;

        public ViewHolder(View view) {
            super(view);
            plato = itemView.findViewById(R.id.plato);
            rating = (RatingBar) itemView.findViewById(R.id.ratingBar);
            rating.setIsIndicator(true);
        }
    }
}
