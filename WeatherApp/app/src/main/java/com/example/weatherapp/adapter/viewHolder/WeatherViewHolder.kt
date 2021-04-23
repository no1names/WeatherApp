package com.example.weatherapp.adapter.viewHolder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemWeatherBinding
import com.example.weatherapp.model.dataclasses.WeatherInfo

class WeatherViewHolder(
    private val binding: ItemWeatherBinding,
    private val onClick: (WeatherInfo) -> Unit,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun setWeatherStatus(weatherInfo: WeatherInfo) {
        weatherInfo.weather.firstOrNull()?.let {
            binding.txWeatherStatus.text = it.main
        }
    }

    fun setWeatherTemp(weatherInfo: WeatherInfo) {

        binding.txWeatherTemp.text =
            context.getString(R.string.temp).plus(weatherInfo.main.temp.toString())
    }

    fun setOnClick(weatherInfo: WeatherInfo) {
        binding.root.setOnClickListener {
            onClick.invoke(weatherInfo)
        }
    }
}