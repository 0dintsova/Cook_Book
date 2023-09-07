package com.example.summerproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.summerproject.R
import com.example.summerproject.database.entities.Recipe
import com.example.summerproject.databinding.RecipeItemBinding

class RecipeAdapter(val listener : Listener):RecyclerView.Adapter<RecipeAdapter.RecipeHolder>(){

    private var recipeList: MutableList<Recipe> = mutableListOf()

    class RecipeHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var binding = RecipeItemBinding.bind(view)

        fun bind(recipe: Recipe, listener: Listener) = with(binding) {
            icon.isChecked = recipe.isFavorite
            nameRecipe.text = recipe.recipeName

            //recipeImage.setImageResource(recipe.image)//Если этот ресурс есть
            //recipeText.text = recipe.

            // Нажатие на рецепт, открытия контент-экрана
            cardView.setOnClickListener {
                listener.onClickRecipe(recipe)
            }

            // Нажатие на иконку liked
            icon.setOnClickListener {

                listener.onClickIcon(recipe.copy(isFavorite = icon.isChecked))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeHolder(item)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.bind(recipeList[position], listener)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    fun submit(list: List<Recipe>) {
        recipeList.clear()
        recipeList.addAll(list)
        notifyDataSetChanged()
    }

    fun likedUpdate(list: List<Recipe>) {
        list.forEach {
         if (!it.isFavorite) recipeList.remove(it)
        }
        notifyDataSetChanged()
    }

    interface Listener {
        fun onClickRecipe(recipe: Recipe)
        fun onClickIcon(recipe: Recipe)
    }


}