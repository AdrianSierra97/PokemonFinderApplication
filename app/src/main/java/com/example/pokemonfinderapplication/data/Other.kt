package com.example.pokemonfinderapplication.data


import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("dream_world")
    val dreamWorld: DreamWorld? = DreamWorld(),
    @SerializedName("home")
    val home: Home? = Home(),
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork? = OfficialArtwork(),
    @SerializedName("showdown")
    val showdown: Showdown? = Showdown()
)