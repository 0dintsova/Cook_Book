package com.example.summerproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.summerproject.R
import com.example.summerproject.database.entities.Ingredients
import com.example.summerproject.databinding.IngredientItemBinding

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.IngredientsHolder>() {
    var ingredientsList = listOf<Ingredients>()

    class IngredientsHolder(view:View):RecyclerView.ViewHolder(view) {
        private val binding = IngredientItemBinding.bind(view)
        fun bind(ingredient:Ingredients){
            binding.IngredientName.text = ingredient.name
            binding.Quantity.text = ingredient.count.toString()
            //binding.ingredientImage.setImageResource()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_item, parent, false)
        return IngredientsHolder(item)
    }

    override fun onBindViewHolder(holder: IngredientsHolder, position: Int) {
        holder.bind(ingredientsList[position])
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    fun setIngredient(ingredients: List<Ingredients>){
        ingredientsList = ingredients
        notifyDataSetChanged()
    }
}