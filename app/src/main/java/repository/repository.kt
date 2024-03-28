package repository

import com.example.pokemonfinderapplication.data.Pokemon

interface PokeDataSource {
    suspend fun getPokemon(number: Int): Pokemon
}