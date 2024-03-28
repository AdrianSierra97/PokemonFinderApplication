package com.example.pokemonfinderapplication.utils

sealed class LoadingState {
    object Loading : LoadingState()
    object Loaded : LoadingState()
    object Error : LoadingState()
}
