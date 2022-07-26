package ru.netology.diploma

import androidx.lifecycle.MutableLiveData

class RecipeRepository {
    private val recipes
        get() = checkNotNull(data.value) { "" }

    val numKinds = KitchenKindEnum.values().size
    val data = MutableLiveData(

        List(20){index ->
            Recipe(
                id = index.toLong(),
                kithenName = "kitchen",
                shortDescription = "Short Description",
                content = "Content"
            )
        }
    )
}