package com.example.pokemonfinderapplication.data


import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("abilities")
    val abilities: List<Ability>? = listOf(),
    @SerializedName("base_experience")
    val baseExperience: Int? = 0,
    @SerializedName("cries")
    val cries: Cries? = Cries(),
    @SerializedName("forms")
    val forms: List<Form>? = listOf(),
    @SerializedName("game_indices")
    val gameIndices: List<GameIndice>? = listOf(),
    @SerializedName("height")
    val height: Int? = 0,
    @SerializedName("held_items")
    val heldItems: List<HeldItem>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("is_default")
    val isDefault: Boolean? = false,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String? = "",
    @SerializedName("moves")
    val moves: List<Move>? = listOf(),
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("order")
    val order: Int? = 0,
    @SerializedName("past_types")
    val pastTypes: List<PastType>? = listOf(),
    @SerializedName("species")
    val species: Species? = Species(),
    @SerializedName("sprites")
    val sprites: Sprites? = Sprites(),
    @SerializedName("stats")
    val stats: List<Stat>? = listOf(),
    @SerializedName("types")
    val types: List<TypeXX>? = listOf(),
    @SerializedName("weight")
    val weight: Int? = 0
)