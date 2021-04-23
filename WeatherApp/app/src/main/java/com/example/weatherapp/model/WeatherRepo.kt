package com.example.weatherapp.model

import com.example.weatherapp.model.remote.WeatherService
import com.example.weatherapp.utils.APIResult
import com.example.weatherapp.utils.Constants.ERROR_RESPONSE_TEXT
import com.example.weatherapp.utils.Constants.NULL_BODY_RESPONSE_TEXT
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepo @Inject constructor(private val weatherService: WeatherService) {
    private val apiKey = "65d00499677e59496ca2f318eb68c049"
    fun getForestWeatherDailyListResponse(city: String, units: String = "imperial") = flow {
        val response = weatherService.getForestWeatherDailyListResponse(city, apiKey, units)
        when (response.code()) {
            200 -> {
                response.body()?.let {
                    emit(APIResult.Success(it))
                } ?: emit(APIResult.Failure(NULL_BODY_RESPONSE_TEXT))
            }
            else -> emit(APIResult.Failure(ERROR_RESPONSE_TEXT))
        }
    }
}