package com.example.esiea3a.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esiea3a.R
import com.example.esiea3a.R.id.navigateToPokemonDetailFragment
import com.example.esiea3a.presentation.Singletons
import com.example.esiea3a.presentation.api.PokemonListResponse
import com.example.esiea3a.presentation.api.PokeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PokemonListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private val adapter = PokemonAdapter(listOf(), ::OnClickedPokemon)

    private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.pokemon_recyclerview)



        recyclerView.apply {
            layoutManager = this@PokemonListFragment.layoutManager
            adapter = this@PokemonListFragment.adapter
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokeApi: PokeApi = retrofit.create(PokeApi::class.java)

            Singletons.pokeApi.getPokemonList().enqueue(object : Callback<PokemonListResponse> {
                override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                    //TODO("Not yet implemented")
                }

                override fun onResponse(
                    call: Call<PokemonListResponse>,
                    response: Response<PokemonListResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val pokemonResponse: PokemonListResponse? = response.body()!!
                        if (pokemonResponse != null) {
                            adapter.updateList(pokemonResponse.results)
                        }
                    }

                }
            })
        }
    private fun OnClickedPokemon(pokemon: Pokemon) {
        findNavController().navigate(navigateToPokemonDetailFragment)
    }
}