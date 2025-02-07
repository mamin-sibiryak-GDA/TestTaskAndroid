package com.example.testtaskandroid.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testtaskandroid.R
import com.example.testtaskandroid.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_search,
                R.id.navigation_favourites,
                R.id.navigation_responses,
                R.id.navigation_messages,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        mainViewModel.getOffers()
        mainViewModel.getVacancies()

        val badge = navView.getOrCreateBadge(R.id.navigation_favourites)
        badge.backgroundColor = getColor(R.color.red)
        mainViewModel.vacancies.observe(this, Observer {
            val num = (it.filter { it.isFavorite }).size
            if (num != 0) {
                badge.isVisible = true
                badge.number = (it.filter { it.isFavorite }).size
            }
            else
                badge.isVisible = false
        })
    }
}