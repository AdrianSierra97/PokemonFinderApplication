package com.example.pokemonfinderapplication.framework.network

import com.example.pokemonfinderapplication.data.Pokemon
import com.example.pokemonfinderapplication.framework.network.NetworkUtility.makeRetrofitRequest
import repository.PokeDataSource

class PokeRepositoryImpl : PokeDataSource {
    private val retrofitService = RetrofitService.buildService(PokeApi::class.java)

    override suspend fun getPokemon(number: Int): Pokemon =
        makeRetrofitRequest { retrofitService.getPokemon(number) }
}