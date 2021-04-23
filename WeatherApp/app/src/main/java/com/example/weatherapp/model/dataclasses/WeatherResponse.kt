package com.example.weatherapp.model.dataclasses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    val list: List<WeatherInfo>
)
