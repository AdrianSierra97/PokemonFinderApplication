package com.example.pokemonfinderapplication.data


import com.google.gson.annotations.SerializedName

data class TypeXX(
    @SerializedName("slot")
    val slot: Int? = 0,
    @SerializedName("type")
    val type: Type? = Type()
)