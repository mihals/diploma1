package ru.netology.diploma

data class Recipe(
    val id:Long,
    val kitchenOrdinal: Int,
    val recipeName: String,
    //val shortDescription:String,
    val content:String
)