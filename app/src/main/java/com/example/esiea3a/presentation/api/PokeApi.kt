package com.example.esiea3a.presentation.api

import com.example.esiea3a.presentation.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET

interface PokeApi {
        @GET("pokemon")
        fun getPokemonList(): Call<PokemonResponse>
}