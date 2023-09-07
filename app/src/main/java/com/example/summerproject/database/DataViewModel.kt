package com.example.summerproject.database

import androidx.lifecycle.*
import com.example.summerproject.database.entities.Recipe
import kotlinx.coroutines.launch

open class DataViewModel(database: RecipeDB): ViewModel() {
    val dao = database.getDao()
    val recipeItems = MutableLiveData<List<Recipe>>()


    val allItems : LiveData<List<Recipe>> = dao.getAllItems().asLiveData()
    val likedItemsList: LiveData<List<Recipe>> = dao.getLikedItems().asLiveData()


    fun getRecipeItems(filter:String) = viewModelScope.launch {
        recipeItems.postValue(
            dao.getAllRecipeItems(filter)
        )
    }
    fun getFavoriteItems(filter:Boolean) = viewModelScope.launch {
        recipeItems.postValue(
            if(filter) dao.getFavoriteRecipeItems(1)
            else dao.getFavoriteRecipeItems(0)
        )
    }

    fun insertItem(recipe:Recipe) = viewModelScope.launch {
        dao.insertItem(recipe)
    }
    fun likedItem(recipe: Recipe) = viewModelScope.launch {
        dao.updateItem(recipe)
    }


    //Инициализация класса ViewModel
    class DataModelFactory(val database: RecipeDB) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(DataViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return DataViewModel(database) as T
            }
            throw IllegalArgumentException("Unknown ViewModelClass")
        }
    }
}