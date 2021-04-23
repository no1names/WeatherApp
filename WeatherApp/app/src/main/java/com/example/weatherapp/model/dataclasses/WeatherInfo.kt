package com.example.weatherapp.model.dataclasses

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class WeatherInfo(
    val dt: String,
    val main: Main,
    val weather: List<Weather>
) : Parcelable
