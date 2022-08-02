package ru.netology.diploma.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val kitchenOrdinal:Int,
    val recipeName:String,
    val content:String
)
