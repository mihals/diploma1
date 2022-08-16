package ru.netology.diploma
//import Collections.kt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import ru.netology.diploma.room.RecipeDao
import ru.netology.diploma.room.toEntity
import ru.netology.diploma.room.toModel

class RecipeRepository(
    private val dao: RecipeDao
) {
    private val recipes
        get() = checkNotNull(data.value) { "" }

    var data = ( Transformations.map(dao.getAll()){ entities ->
        entities.map{it.toModel()}
    }) as MutableLiveData<List<Recipe>>

    fun getByKitchenId(id: Int) {
        if (id == 0) {
            data = (Transformations.map(dao.getAll()) { entities ->
                entities.map { it.toModel() }
            } as MutableLiveData<List<Recipe>>)
            return
        }

        data = (Transformations.map(dao.getByKitchenId(id-1)) { entities ->
            entities.map { it.toModel() }
        } as MutableLiveData<List<Recipe>>)
    }

    fun save(recipe: Recipe){
        dao.insert(recipe.toEntity())
    }

    fun getById(recipeId: Long):Recipe{
        val recipe = dao.getById(recipeId)
        return recipe
    }

    fun update(recipe: Recipe){
        dao.update(recipe.toEntity())
    }

    fun delete(recipe: Recipe){
        dao.delete(recipe.toEntity())
    }
}