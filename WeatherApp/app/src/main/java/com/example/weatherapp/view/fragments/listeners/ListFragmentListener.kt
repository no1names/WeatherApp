package com.example.weatherapp.view.fragments.listeners

import com.example.weatherapp.model.dataclasses.WeatherInfo

interface ListFragmentListener {
    fun toDetailFragment(weatherInfo: WeatherInfo, city: String)
}