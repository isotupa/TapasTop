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

import java.util.List;
import java.util.Random;

public class PlatoEvaluableAdapter extends RecyclerView.Adapter<PlatoEvaluableAdapter.ViewHolder>{
    List<Degustacion> degustaciones;
    //Context context;

    // Constructor for initialization
    public PlatoEvaluableAdapter(List<Degustacion> degustaciones) {
        this.degustaciones = degustaciones;
    }
    @NonNull
    @Override
    public PlatoEvaluableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item_degustaciones, parent, false);

        // Passing view to ViewHolder
        PlatoEvaluableAdapter.ViewHolder viewHolder = new PlatoEvaluableAdapter.ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull PlatoEvaluableAdapter.ViewHolder holder, int position) {

        Random random = new Random();
        int number = random.nextInt(100000);

        holder.plato.setText(number+"");
        //holder.restaurante.setText(Modelo.get_Restaurante(degustaciones.get(position).getId_plato()).getNombre());
        holder.restaurante.setText("hai");
        holder.rating.setRating(Integer.parseInt(degustaciones.get(position).getCalificacion()));
        //holder.rating.setRating((float)3.5);
        //holder.restaurante.setText("un restaurante");
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
            plato = itemView.findViewById(R.id.plato);
            restaurante = itemView.findViewById(R.id.restaurante);
            rating = (RatingBar) itemView.findViewById(R.id.ratingBar);
            rating.setIsIndicator(true);
        }
    }
}
