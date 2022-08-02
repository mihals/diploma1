package ru.netology.diploma

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.netology.diploma.room.AppDb
import ru.netology.nmedia.util.SingleLiveEvent

class RecipeViewModel(application:Application
):AndroidViewModel(application) {
    private val repository = RecipeRepository(
        dao = AppDb.getInstance(context = application).recipeDao)
    val navigateToSingleRecipeFragmentEvent = SingleLiveEvent<Long>()
    val data by repository::data

    var selectedKitchenKindItem = 0

    fun onAddNewRecipe(recipe: Recipe){
        repository.save(recipe)
    }

    fun onRemoveRecipeClicked(recipe: Recipe){
        repository.delete(recipe)
    }

    fun onRecipeCardClicked(recipeId: Long) {
        navigateToSingleRecipeFragmentEvent.value = recipeId
    }

    fun getById(recipeId: Long):Recipe{
        val recipe = repository.getById(recipeId)
        return recipe
    }

    fun update(recipe: Recipe){
        repository.update(recipe)
    }
}