package com.example.pokemonfinderapplication.data


import com.google.gson.annotations.SerializedName

data class VersionDetail(
    @SerializedName("rarity")
    val rarity: Int? = 0,
    @SerializedName("version")
    val version: Version? = Version()
)