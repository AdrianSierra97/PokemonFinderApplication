package com.example.pokemonfinderapplication.data


import com.google.gson.annotations.SerializedName

data class GenerationIi(
    @SerializedName("crystal")
    val crystal: Crystal? = Crystal(),
    @SerializedName("gold")
    val gold: Gold? = Gold(),
    @SerializedName("silver")
    val silver: Silver? = Silver()
)