package com.example.pokemonfinderapplication.data


import com.google.gson.annotations.SerializedName

data class PastType(
    @SerializedName("generation")
    val generation: Generation? = Generation(),
    @SerializedName("types")
    val types: List<Type>? = listOf()
)