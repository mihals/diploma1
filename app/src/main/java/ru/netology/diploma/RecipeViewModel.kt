package ru.netology.diploma

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class RecipeViewModel(application:Application
):AndroidViewModel(application) {
    private val repository = RecipeRepository()
    val data by repository::data
}