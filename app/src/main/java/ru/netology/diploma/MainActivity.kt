package ru.netology.diploma

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewModel:RecipeViewModel by viewModels ()
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(findViewById(R.id.bottom_nav))
//        var isVis:Boolean
//        val myBottomBar:BottomNavigationView = findViewById(R.id.bottom_nav)
//        myBottomBar.setOnItemSelectedListener {item ->
//            when(item.itemId){
//                R.id.details_page_fragment ->
//                isVis = myBottomBar.isVisible
//
//            }
//            true
//        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_main,R.id.nav_favorite))
        setupActionBarWithNavController(navController,appBarConfiguration)
//        setContentView(R.layout.activity_searchable)
//        if (Intent.ACTION_SEARCH == intent.action) {
//            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
//                //doMySearch(query)
//            }
//        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }
}