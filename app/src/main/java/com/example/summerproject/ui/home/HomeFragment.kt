package com.example.summerproject.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.summerproject.*
import com.example.summerproject.database.DataViewModel
import com.example.summerproject.database.entities.Recipe
import com.example.summerproject.databinding.FragmentHomeBinding
import com.example.summerproject.adapter.MealTimeAdapter
import com.example.summerproject.adapter.RecipeAdapter

class HomeFragment : Fragment(), MealTimeAdapter.Listener, RecipeAdapter.Listener {
    private lateinit var binding: FragmentHomeBinding
    private var mealTime: String = "breakfast"

    //Адаптеры
    private val mealAdapter = MealTimeAdapter(this@HomeFragment)
    private var recipeAdapter: RecipeAdapter? = null

    // Инициализация viewModel
    private val dataViewModel: DataViewModel by activityViewModels {
        DataViewModel.DataModelFactory((context?.applicationContext as MainApp).db)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        //createRecipe()
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    //Изменение иконок с приемами пищи при нажатии
    @SuppressLint("SuspiciousIndentation")
    override fun onClickPressed(mealItem: MealTime, list: List<MealTime>) {
        mealTime = mealItem.name
        Log.d("ddddd", mealTime)

        for (item in list) {
            item.backgr = R.drawable.unpressed_item
            when (item.name) {
                getString(R.string.Breakfast) -> item.image = R.drawable.pancakes
                getString(R.string.Lunch) -> item.image = R.drawable.shallow_pan_of_food
                getString(R.string.Dinner) -> item.image = R.drawable.pot_of_food
                getString(R.string.Salads) -> item.image = R.drawable.green_salad
                getString(R.string.Snacks) -> item.image = R.drawable.pretzel
                getString(R.string.Desserts) -> item.image = R.drawable.ice_cream
                getString(R.string.Drinks) -> item.image = R.drawable.drink
            }
            if (mealItem.name == item.name) {
                item.backgr = R.drawable.pressed_item
                when (item.name) {
                    getString(R.string.Breakfast) -> item.image = R.drawable.pancakes_press
                    getString(R.string.Lunch) -> item.image = R.drawable.shallow_pan_of_food_press
                    getString(R.string.Dinner) -> item.image = R.drawable.pot_of_food_press
                    getString(R.string.Salads) -> item.image = R.drawable.green_salad_press
                    getString(R.string.Snacks) -> item.image = R.drawable.pretzel_press
                    getString(R.string.Desserts) -> item.image = R.drawable.ice_cream_press
                    getString(R.string.Drinks) -> item.image = R.drawable.drink_press
                }
            }
        }
        mealItemObserver()
        mealAdapter.upDataMealList(list)
    }

    private fun mealItemObserver(){
        dataViewModel.getRecipeItems(mealTime)
        dataViewModel.recipeItems.observe(this) {
            recipeAdapter!!.submit(it)
        }
    }

    // Обработка нажатия на элемент списка
    override fun onClickRecipe(recipe: Recipe) {
        val intent = Intent(context, ContentActivity::class.java)
        intent.putExtra("item", recipe)
        startActivity(intent)
    }

    // Обработка нажатия на иконку like
    override fun onClickIcon(recipe: Recipe) {
        dataViewModel.likedItem(recipe)
        mealItemObserver()
    }

    // Инициализация RecyclerViews
    private fun initRV() {
        // Список с приемами пищи
        val mealRecyclerView: RecyclerView = binding.mealList
        mealRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mealRecyclerView.adapter = mealAdapter


        // Список с рецептами
        val recipeRecyclerView: RecyclerView = binding.mainRecipeList
        recipeAdapter = RecipeAdapter(this@HomeFragment)
        recipeRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recipeRecyclerView.adapter = recipeAdapter
    }


    private fun createRecipe() {
            dataViewModel.insertItem(
            Recipe(
                null,
                "Dinner",
                R.drawable.rectangle,
                "Разогреть сковородку, разбить да яйца, через 10 мин готово",
                453f,34f,434f,3f, false,"Dinner",""

        ))
        dataViewModel.insertItem(
            Recipe(
                null,
                "Salad",
                R.drawable.rectangle2,
                "Разогреть сковородку, разбить да яйца, через 10 мин готово",
                453f,34f,434f,3f, false,"Salads",""

            ))
             dataViewModel.insertItem(
            Recipe(
                null,
                "Lunch",
                R.drawable.rectangl2,
                "Разогреть сковородку, разбить да яйца, через 10 мин готово",
                453f,34f,434f,3f, false,"Lunch",""

            ))

    }
}