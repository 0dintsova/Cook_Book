package com.example.summerproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.summerproject.database.entities.Recipe
import com.example.summerproject.databinding.ActivityContentBinding
import com.example.summerproject.adapter.IngredientsAdapter
import com.example.summerproject.database.DataViewModel

@Suppress("DEPRECATION")
class ContentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Инициализация viewModel
        val dataViewModel: DataViewModel by viewModels {
            DataViewModel.DataModelFactory((this?.applicationContext as MainApp).db)
        }

        initRcView()
        val item = intent.getSerializableExtra("item") as Recipe
        binding.icon.isChecked = item.isFavorite

        binding.imBack.setOnClickListener {
            finish()
        }

        binding.icon.setOnClickListener{
            item.isFavorite = binding.icon.isChecked
            binding.icon.isChecked = item.isFavorite
            dataViewModel.likedItem(item.copy(isFavorite = item.isFavorite))

        }




    }

    private fun initRcView(){
        val ingredientsRecyclerView:RecyclerView = binding.ingredientsRecyclerView
        val ingredientsAdapter = IngredientsAdapter()
        ingredientsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        ingredientsRecyclerView.adapter = ingredientsAdapter
    }
}