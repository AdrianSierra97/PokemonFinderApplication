package com.example.pokemonfinderapplication.data


import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue")
    val redBlue: RedBlue? = RedBlue(),
    @SerializedName("yellow")
    val yellow: Yellow? = Yellow()
)