package com.example.t3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pokemonDetailActivity extends AppCompatActivity {

    ImageView image_pokemon;
    EditText nombre, tipo;
    private Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        image_pokemon = findViewById(R.id.image_poke);
        nombre = findViewById(R.id.namePoke);
        tipo = findViewById(R.id.typePoke);

        String ID = getIntent().getStringExtra("ID");
        Log.e("ID DET ", ID);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service service = retrofit.create(Service.class);
        Call<Pokemon> getDetalle = service.getPokemonID(ID);

        getDetalle.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                pokemon = response.body();

                String im = "https://upn.lumenes.tk" + pokemon.getUrl_imagen();
                Picasso.get()
                        .load(im)
                        .into(image_pokemon);
                nombre.setText(pokemon.getNombre());
                tipo.setText(pokemon.getTipo());
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });

        findViewById(R.id.capt_poke).setOnClickListener(v -> {
            Pokemon pokemon = new Pokemon();
            pokemon.setPokemon_id(ID);

            Call<Pokemon> capP = service.postCapturarPokemon(pokemon);

            capP.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    String respuesta = String.valueOf(response.code());
                    if (respuesta.equals("200")) {
                        Toast.makeText(getApplicationContext(), "Pokemon Capturado", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    } else {
                        Toast.makeText(getApplicationContext(), "Pokemon no Capturado", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {

                }
            });
        });
        findViewById(R.id.location_poke).setOnClickListener(view -> {

            String lati = String.valueOf(pokemon.getLatitude());
            String longi = String.valueOf(pokemon.getLongitude());

            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("lati", lati);
            intent.putExtra("longi", longi);

            startActivity(intent);
        });
    }
}