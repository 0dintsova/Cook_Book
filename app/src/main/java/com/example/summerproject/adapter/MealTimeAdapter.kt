package com.example.summerproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.summerproject.MealTime
import com.example.summerproject.R
import com.example.summerproject.databinding.MealItemBinding

class MealTimeAdapter(val listener : Listener): RecyclerView.Adapter<MealTimeAdapter.ViewHolder>() {

    private var mealTimeList : List<MealTime> = listOf(
        MealTime("Breakfast", R.drawable.pancakes_press, R.drawable.pressed_item),
        MealTime("Lunch", R.drawable.shallow_pan_of_food, R.drawable.unpressed_item),
        MealTime("Dinner", R.drawable.pot_of_food, R.drawable.unpressed_item),
        MealTime("Salads", R.drawable.green_salad, R.drawable.unpressed_item),
        MealTime("Desserts", R.drawable.ice_cream, R.drawable.unpressed_item),
        MealTime("Drinks", R.drawable.drink, R.drawable.unpressed_item),
        MealTime("Snacks", R.drawable.pretzel, R.drawable.unpressed_item)
    )

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        private val binding = MealItemBinding.bind(view)

        fun bind(mealTime: MealTime,list: List<MealTime>,listener:Listener) = with(binding){

            itemView.setOnClickListener{
                listener.onClickPressed(mealTime,list)
            }

            mealName.text = mealTime.name
            mealImage.setImageResource(mealTime.image)
            card.setBackgroundResource(mealTime.backgr)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.meal_item,parent,false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mealTimeList[position], mealTimeList, listener)
    }

    override fun getItemCount(): Int {
        return mealTimeList.size
    }

    fun upDataMealList(list: List<MealTime>){
        mealTimeList = list
        notifyDataSetChanged()
    }
    interface Listener{
        fun onClickPressed(item:MealTime, list: List<MealTime>)

    }


}

