package com.example.t3;

import android.content.Context;
import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterPokemon extends RecyclerView.Adapter<adapterPokemon.viewPokemon> {
    private final List<Pokemon> list;
    private final Context mContext;

    public adapterPokemon(List<Pokemon> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public adapterPokemon.viewPokemon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewPokemon(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_pokemon,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull adapterPokemon.viewPokemon holder, int position) {

        Pokemon pokemon = list.get(position);
        Picasso.get().load("https://upn.lumenes.tk/"+pokemon.getUrl_imagen()).into(holder.image_pokemon);
        holder.text_pokemon.setText(pokemon.getNombre());
        holder.text_description.setText(pokemon.getTipo());

        String ID = String.valueOf(pokemon.getId());

        holder.btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,pokemonDetailActivity.class);

                intent.putExtra("ID", ID);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static class viewPokemon extends RecyclerView.ViewHolder {
        ImageView image_pokemon;
        TextView text_pokemon, text_description;
        CardView card_click;
        Button btnDetalle;

        public viewPokemon(@NonNull View itemView) {
            super(itemView);

            image_pokemon = itemView.findViewById(R.id.image_pokemon);
            text_pokemon = itemView.findViewById(R.id.text_pokemon);
            text_description = itemView.findViewById(R.id.text_description);
            card_click = itemView.findViewById(R.id.card_click);
            btnDetalle = itemView.findViewById(R.id.btnDetalle);


        }
    }
}
