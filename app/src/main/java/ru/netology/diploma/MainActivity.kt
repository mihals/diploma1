package ru.netology.diploma

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
    val kitchenKind = KitchenKindEnum.Asian
    private val viewModel:RecipeViewModel by viewModels()
}