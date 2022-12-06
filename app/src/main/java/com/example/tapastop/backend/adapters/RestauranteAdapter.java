package com.example.tapastop.backend.adapters;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapastop.Entidades.Restaurante;
import com.example.tapastop.R;
import com.example.tapastop.RestaurantScreenActivity;

import java.util.List;

public class RestauranteAdapter extends RecyclerView.Adapter<RestauranteAdapter.ViewHolder>{
    List<Restaurante> restaurantes;
    //Context context;
    public String dirs;

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
        dirs = restaurantes.get(position).getDireccion()+"";
    }

    @Override
    public int getItemCount() {
        return restaurantes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView text;
        TextView dir;

        Button btn;

        public ViewHolder(View view) {
            super(view);
            text = itemView.findViewById(R.id.plato);
            dir = itemView.findViewById(R.id.plato2);
            btn = itemView.findViewById(R.id.gotoRestaurantBtn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), RestaurantScreenActivity.class);
                    intent.putExtra("restaurantName", text.getText().toString());
                    intent.putExtra("restaurantAddress", dirs);
                    view.getContext().startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), RestaurantScreenActivity.class);
            Toast.makeText(view.getContext(), "holaa", Toast.LENGTH_LONG).show();
            view.getContext().startActivity(intent);
        }
    }
}
