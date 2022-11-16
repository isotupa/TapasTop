package com.example.tapastop.backend.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapastop.Entidades.Restaurante;
import com.example.tapastop.R;

import java.util.ArrayList;
import java.util.List;

public class RestauranteAdapter extends RecyclerView.Adapter<RestauranteAdapter.ViewHolder>{
    List<Restaurante> restaurantes;
    //Context context;

    // Constructor for initialization
    public RestauranteAdapter(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }
    @NonNull
    @Override
    public RestauranteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item, parent, false);

        // Passing view to ViewHolder
        RestauranteAdapter.ViewHolder viewHolder = new RestauranteAdapter.ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RestauranteAdapter.ViewHolder holder, int position) {
        holder.text.setText(restaurantes.get(position).getNombre()+"");
        holder.dir.setText(restaurantes.get(position).getDireccion()+"");
    }
    @Override
    public int getItemCount() {
        return restaurantes.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        TextView dir;

        public ViewHolder(View view) {
            super(view);
            text = itemView.findViewById(R.id.plato);
            dir = itemView.findViewById(R.id.plato2);
        }
    }
}
