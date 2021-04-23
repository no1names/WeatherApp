package com.example.weatherapp.model.remote

import com.example.weatherapp.model.dataclasses.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/data/2.5/forecast")
    suspend fun getForestWeatherDailyListResponse(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") string: String
    ): Response<WeatherResponse>
}