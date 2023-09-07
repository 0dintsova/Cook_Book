package com.example.summerproject.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.summerproject.ContentActivity
import com.example.summerproject.MainApp
import com.example.summerproject.database.DataViewModel
import com.example.summerproject.database.entities.Recipe
import com.example.summerproject.databinding.FragmentSavedBinding
import com.example.summerproject.adapter.RecipeAdapter


class SavedFragment : Fragment(), RecipeAdapter.Listener{
    lateinit var binding: FragmentSavedBinding
    private var recipeAdapter: RecipeAdapter? = null
    // Инициализация viewModel
    private val dataViewModel: DataViewModel by activityViewModels {
        DataViewModel.DataModelFactory((context?.applicationContext as MainApp).db)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()

        //отображение понравившихся рецептов
        observe()
    }

    override fun onClickRecipe(recipe: Recipe) {
        val intent = Intent(context, ContentActivity::class.java)
        intent.putExtra("item", recipe)
        startActivity(intent)
    }

    override fun onClickIcon(recipe: Recipe) {
        dataViewModel.likedItem(recipe)
        observeLikedItems()
    }

    // Отображение списка с понравившимися элементами
    private fun observe(){
        dataViewModel.getFavoriteItems(true)
        dataViewModel.recipeItems.observe(this){
            recipeAdapter!!.submit(it)
        }
    }

    private fun observeLikedItems(){
        dataViewModel.getFavoriteItems(true)
        dataViewModel.recipeItems.observe(this){
            recipeAdapter!!.likedUpdate(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SavedFragment()
    }

    fun initRcView(){
        val recipeRecyclerView: RecyclerView = binding.likedRecycler
        recipeAdapter = RecipeAdapter(this@SavedFragment)
        recipeRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recipeRecyclerView.adapter = recipeAdapter
    }


}