package com.example.esiea3a.presentation

import com.example.esiea3a.presentation.api.PokeApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Singletons {
    companion object{
        val pokeApi: PokeApi =Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApi::class.java)

    }
}

