package com.example.t3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Para ver los Pokemons
        Button button = findViewById(R.id.btnMisPokemons);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), pokemonsActivity.class);
                startActivity(intent);
            }
        });


        // Para Registrar

        Button button2 = findViewById(R.id.btnRegistrarPokemon);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), registrarPokemonActivity.class);
                startActivity(intent);
            }
        });

        // Para ver los Capturados

        Button button3 = findViewById(R.id.btnCapturarPokemons);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), pokemonsCapturadosActivity.class);
                startActivity(intent);
            }
        });
    }
}