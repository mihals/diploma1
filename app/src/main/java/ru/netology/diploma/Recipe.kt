package ru.netology.diploma

data class Recipe(
    val id:Long,
    val kitchenOrdinal: Int,
    val recipeName: String,
    val content:String,
    val isFavorite:Boolean = false
)