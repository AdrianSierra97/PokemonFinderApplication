package com.example.pokemonfinderapplication.framework.network


import com.example.pokemonfinderapplication.data.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    @GET("pokemon/{number}")
    suspend fun getPokemon(@Path("number") number: Int): Response<Pokemon>
}