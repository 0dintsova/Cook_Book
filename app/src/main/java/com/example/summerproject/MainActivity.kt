package com.example.summerproject

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.summerproject.database.DataViewModel
import com.example.summerproject.databinding.ActivityMainBinding
import com.example.summerproject.ui.home.HomeFragment
import com.example.summerproject.fragments.ProfileFragment
import com.example.summerproject.fragments.SavedFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataModel : DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val navView: BottomNavigationView = binding.navigation
        setContentView(binding.root)
        replaceFrag(R.id.nav_host_fragment_activity_main,HomeFragment.newInstance())

        // Нижняя навигация
        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFrag(R.id.nav_host_fragment_activity_main,HomeFragment.newInstance())
                R.id.profile -> replaceFrag(R.id.nav_host_fragment_activity_main, ProfileFragment.newInstance())
                R.id.saved -> replaceFrag(R.id.nav_host_fragment_activity_main, SavedFragment.newInstance())
                else->{}
            }
            true
        }
    }
    //Смена фрагментов
    private fun replaceFrag(idHolder:Int, fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(idHolder,fragment)
            .commit()
    }
}
