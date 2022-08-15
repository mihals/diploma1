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
    val changeKitchenKindEvent = SingleLiveEvent<Int>()
    val data by repository::data
    var selectedKitchenId = -1

    var selectedKitchenKindItem = 0

//    fun getByKitchenId(id:Int){
//        if(id!=0)
//        repository.getByKitchenId(id-1)
//        else repository.s
//    }

    fun onAddNewRecipe(recipe: Recipe){
        repository.save(recipe)
    }

    fun onRemoveRecipeClicked(recipe: Recipe){
        repository.delete(recipe)
    }

    fun onRecipeCardClicked(recipeId: Long) {
        navigateToSingleRecipeFragmentEvent.value = recipeId
    }

    fun onSelectKitchenKind(kitchenKindId: Int) {
        repository.getByKitchenId(kitchenKindId)
        if(kitchenKindId in 0..KitchenKindEnum.values().size) {
            changeKitchenKindEvent.value = kitchenKindId
        }
    }

    fun getById(recipeId: Long):Recipe{
        val recipe = repository.getById(recipeId)
        return recipe
    }

    fun update(recipe: Recipe){
        repository.update(recipe)
    }

}