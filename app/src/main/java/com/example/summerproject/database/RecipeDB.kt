package com.example.summerproject.database

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec
import com.example.summerproject.database.entities.CookingSteps
import com.example.summerproject.database.entities.Ingredients
import com.example.summerproject.database.entities.Recipe
import java.time.Instant


@Database(entities = [Recipe::class, Ingredients::class, CookingSteps::class],
    version = 4,
    exportSchema = true,
    autoMigrations = [AutoMigration(from = 3, to = 4)])


abstract class RecipeDB: RoomDatabase(){

    abstract fun getDao():Dao



    companion object {
        @Volatile
        private var INSTANCE: RecipeDB? = null
        fun getRecipeDB(context: Context): RecipeDB {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder (
                    context.applicationContext,
                    RecipeDB::class.java,
                    "Recipes.db"
                ).build()
            }
        }

    }
}



