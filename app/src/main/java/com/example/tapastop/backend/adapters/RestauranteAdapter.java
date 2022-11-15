package com.example.tapastop.backend.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapastop.R;

import java.util.ArrayList;

public class RestauranteAdapter extends RecyclerView.Adapter<RestauranteAdapter.ViewHolder>{
    ArrayList Img, Name;
    //Context context;

    // Constructor for initialization
    public RestauranteAdapter(ArrayList Img, ArrayList Name) {
        this.Img = Img;
        this.Name = Name;
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
        int res = (int) Img.get(position);
        holder.images.setImageResource(res);
        holder.text.setText(Name.get(position)+"");
    }
    @Override
    public int getItemCount() {
        return Img.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView images;
        TextView text;

        public ViewHolder(View view) {
            super(view);
            images = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.plato);
        }
    }
}
