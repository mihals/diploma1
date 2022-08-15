package ru.netology.diploma

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewModel:RecipeViewModel by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_searchable)
//        if (Intent.ACTION_SEARCH == intent.action) {
//            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
//                //doMySearch(query)
//            }
//        }
    }
    val kitchenKind = KitchenKindEnum.Asian
    //private val viewModel:RecipeViewModel by viewModels()
}