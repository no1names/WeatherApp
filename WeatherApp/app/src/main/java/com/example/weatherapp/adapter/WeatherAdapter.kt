package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.adapter.viewHolder.WeatherViewHolder
import com.example.weatherapp.databinding.ItemWeatherBinding
import com.example.weatherapp.model.dataclasses.WeatherInfo

class WeatherAdapter(
    private val weathers: List<WeatherInfo>,
    private val onClick: (WeatherInfo) -> Unit
) : RecyclerView.Adapter<WeatherViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding, onClick, parent.context)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherInfo = weathers[position]
        with(holder) {
            setOnClick(weatherInfo)
            setWeatherStatus(weatherInfo)
            setWeatherTemp(weatherInfo)
        }
    }

    override fun getItemCount(): Int {
        return weathers.size
    }

}