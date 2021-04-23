package com.example.weatherapp.model.dataclasses

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Main(
    val temp: Double,
    @Json(name = "feels_like")
    val feelsLike: Double,
) : Parcelable
