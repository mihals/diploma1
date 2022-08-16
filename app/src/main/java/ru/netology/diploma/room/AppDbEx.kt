package ru.netology.diploma.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(
    entities = [RecipeEntity::class],
    version = 1
)

abstract class AppDbEx:RoomDatabase() {
    abstract val recipeDao:RecipeDao

    companion object{
        @Volatile
        private var instanse:AppDbEx? = null

        fun getInstance(context:Context):AppDbEx{
            return instanse?: synchronized(this){
                instanse?: buildDatabase(context).also{ instanse=it}
            }
        }

        private fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context, AppDbEx::class.java, "app_ex.db"
            ).allowMainThreadQueries().build()

    }
}