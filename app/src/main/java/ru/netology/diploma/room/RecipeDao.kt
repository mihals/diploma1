package ru.netology.diploma.room

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.netology.diploma.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes ORDER BY id DESC")
    fun getAll():LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE id=:id")
    fun getById(id:Long):Recipe

    @Insert
    fun insert(recipe:RecipeEntity)

    @Update
    fun update(recipe: RecipeEntity)

    @Delete
    fun delete(recipe: RecipeEntity)
}