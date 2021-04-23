package com.example.weatherapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.dataclasses.WeatherInfo
import com.example.weatherapp.view.fragments.ListFragmentDirections
import com.example.weatherapp.view.fragments.SearchFragmentDirections
import com.example.weatherapp.view.fragments.listeners.ListFragmentListener
import com.example.weatherapp.view.fragments.listeners.SearchFragmentListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchFragmentListener, ListFragmentListener {

    private val navHost by lazy {
        supportFragmentManager.findFragmentById(binding.fgHost.id) as NavHostFragment
    }

    private val navController by lazy {
        navHost.navController
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun setToolbar(city: String = "", isVisible: Boolean = false) {
        binding.tbWeather.isVisible = isVisible
        binding.tbWeather.title = city
        binding.tbWeather.setNavigationOnClickListener {
            backNavigation()
        }
    }

    override fun toListFragment(city: String) {
        if (city.isEmpty().not()) {
            val action = SearchFragmentDirections.actionSearchFragmentToListFragment(city)
            navController.navigate(action)
        }
    }

    override fun toDetailFragment(weatherInfo: WeatherInfo, city: String) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(weatherInfo, city)
        navController.navigate(action)
    }

    private fun backNavigation() {
        navController.popBackStack()
    }
}