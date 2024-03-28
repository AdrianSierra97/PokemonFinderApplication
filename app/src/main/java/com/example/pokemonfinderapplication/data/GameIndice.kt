package com.example.pokemonfinderapplication.data


import com.google.gson.annotations.SerializedName

data class GameIndice(
    @SerializedName("game_index")
    val gameIndex: Int? = 0,
    @SerializedName("version")
    val version: Version? = Version()
)