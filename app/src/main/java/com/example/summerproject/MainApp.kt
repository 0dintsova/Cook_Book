package com.example.summerproject

import android.app.Application
import com.example.summerproject.database.RecipeDB

class MainApp : Application() {
    //База данных
    val db by lazy { RecipeDB.getRecipeDB(this)}
}