package com.example.tapastop.backend.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapastop.Entidades.Degustacion;
import com.example.tapastop.Entidades.Plato_comida;
import com.example.tapastop.R;
import com.example.tapastop.backend.sqlte.Modelo;

import java.util.List;

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
        Plato_comida p = platos.get(position);
        if(p.getFoto() == null) {
            holder.img.setImageResource(R.drawable.iconmonstr_error_filled);
        } else {
            byte[] blob = p.getFoto();
            Bitmap bmp = BitmapFactory.decodeByteArray(blob,0,blob.length);
            holder.img.setImageBitmap(bmp);
        }

        List<Degustacion> l = Modelo.listar_degustaciones_restaurante(platos.get(position).getId());
        double avg = 0;
        for(int i = 0; i < l.size(); i++) {
            avg += Integer.parseInt(l.get(i).getCalificacion());
        }
        if(l.size() > 0) {
            avg = avg/l.size();
            holder.rating.setVisibility(View.VISIBLE);
            holder.rating.setRating((float) avg);
        }
        else {
            holder.rating.setVisibility(View.INVISIBLE);
            holder.info.setVisibility(View.VISIBLE);
        }
        //holder.rating.setRating((float)3.5);
        //holder.restaurante.setText("un restaurante");
    }

    @Override
    public int getItemCount() {
        return platos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView plato;
        TextView info;
        RatingBar rating;
        ImageView img;

        public ViewHolder(View view) {
            super(view);
            plato = itemView.findViewById(R.id.galardon);
            rating = (RatingBar) itemView.findViewById(R.id.ratingBar);
            rating.setIsIndicator(true);
            info = itemView.findViewById(R.id.textView8);
            info.setVisibility(View.INVISIBLE);
            img = itemView.findViewById(R.id.fotoPlato);
        }
    }
}
