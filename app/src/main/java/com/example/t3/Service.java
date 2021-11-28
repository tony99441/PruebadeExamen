package com.example.t3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Service {

    @GET("N00017665")
    Call<List<Pokemon>> getPokemones();

    @POST("N00017665/crear")
    Call<Void> postCrearPokemon(@Body Pokemon pokemon);

    @GET("pokemones/{ID}")
    Call<Pokemon> getPokemonID(@Path("ID") String id);

    @POST("entrenador/N00017665/pokemon")
    Call<Pokemon> postCapturarPokemon(@Body Pokemon cap);

    @GET("entrenador/N00017665/pokemones")
    Call<List<Pokemon>> getPokemonesCapturados();

}
