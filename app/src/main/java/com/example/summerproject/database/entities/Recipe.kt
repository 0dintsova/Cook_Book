package com.example.summerproject.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity (tableName = "recipes")
data class Recipe(
    //Автоматическая генерация id
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    //Название колонн
    @ColumnInfo(name = "name")
    val recipeName:String,

    @ColumnInfo(name = "image")
    val image: Int,

    @ColumnInfo(name = "steps")
    val text: String,

    @ColumnInfo(name = "calories", defaultValue = "0")
    val calories: Float,

    @ColumnInfo(name = "protein", defaultValue = "0")
    val protein: Float,

    @ColumnInfo(name = "fats", defaultValue = "0")
    val fats: Float,

    @ColumnInfo(name = "carbohydrates", defaultValue = "0")
    val carbohydrates: Float,

    @ColumnInfo(name = "is_favorite", defaultValue = "0")
    var isFavorite: Boolean ,

    @ColumnInfo(name = "meal_time", defaultValue = "")
    val mealTimeFilter:String,

    @ColumnInfo(name = "ingredients_ids", defaultValue = "0")
    val IngredientsIds: String,
    ): Serializable



