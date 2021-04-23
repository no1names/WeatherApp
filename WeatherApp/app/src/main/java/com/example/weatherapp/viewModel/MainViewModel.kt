package com.example.weatherapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherRepo
import com.example.weatherapp.model.dataclasses.WeatherResponse
import com.example.weatherapp.utils.APIResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val weatherRepo: WeatherRepo) : ViewModel() {

    private val _weatherAPIResult = MutableLiveData<APIResult<WeatherResponse>>()

    val weatherAPIResult: LiveData<APIResult<WeatherResponse>>
        get() = _weatherAPIResult

    fun fetchWeatherResponse(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepo.getForestWeatherDailyListResponse(city).collect {
                _weatherAPIResult.postValue(it)
            }
        }
    }
}