package com.example.summerproject.database
import androidx.room.*
import androidx.room.Dao
import com.example.summerproject.database.entities.Recipe
import kotlinx.coroutines.flow.Flow
import org.jetbrains.annotations.NotNull

@Dao
interface Dao {
    //Добавить элемент в бд
    @Insert
    suspend fun insertItem(recipe: Recipe)

    @Update
    suspend fun updateItem(recipe: Recipe)

    //Получить данные
    @Query("SELECT * FROM recipes")
    fun getAllItems(): Flow<List<Recipe>>

    //Фильтрация
    @Query("SELECT * FROM recipes WHERE meal_time = (:mealTime) ")
    suspend fun getAllRecipeItems(mealTime: String): List<Recipe>

    @Query("SELECT * FROM recipes WHERE is_favorite = (:isFavorite) ")
    suspend fun getFavoriteRecipeItems(isFavorite: Int): List<Recipe>

    @Query("SELECT * FROM recipes WHERE is_favorite = 1")
    fun getLikedItems(): Flow<List<Recipe>>

    @Query("DELETE FROM recipes")
    suspend fun deleteAll()




    // ??? Flow - Корутин, нужен для обновления инфы в приложении при изменении инфы в бд
}