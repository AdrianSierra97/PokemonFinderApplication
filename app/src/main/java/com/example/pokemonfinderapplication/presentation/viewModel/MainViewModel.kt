package com.example.pokemonfinderapplication.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokemonfinderapplication.data.Pokemon
import com.example.pokemonfinderapplication.framework.network.PokeRepositoryImpl
import com.example.pokemonfinderapplication.utils.LoadingState
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel : BaseViewModel() {
    val pokeRepositoryImpl = PokeRepositoryImpl()
    protected var _pokemon = MutableLiveData<Pokemon>()
    val pokemon: MutableLiveData<Pokemon>
        get() = _pokemon

    fun loadData() {
        viewModelScope.launch {
            _loadingState.value = LoadingState.Loading
            try {
                _pokemon.value = pokeRepositoryImpl.getPokemon(Random.nextInt(1, 1026))
                _loadingState.value = LoadingState.Loaded

            } catch (e: Exception) {
                _loadingState.value = LoadingState.Error
            }
        }
    }
}