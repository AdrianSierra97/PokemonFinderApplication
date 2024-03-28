package com.example.pokemonfinderapplication.data


import com.google.gson.annotations.SerializedName

data class Form(
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("url")
    val url: String? = ""
)