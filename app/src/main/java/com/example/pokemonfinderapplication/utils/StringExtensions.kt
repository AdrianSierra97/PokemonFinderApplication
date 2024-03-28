package com.example.pokemonfinderapplication.utils

fun String.capitalizeFirstLetter(): String {
    return replaceFirstChar { it.uppercase() }
}