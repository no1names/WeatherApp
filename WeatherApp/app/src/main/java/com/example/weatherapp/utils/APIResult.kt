package com.example.weatherapp.utils

sealed class APIResult<out T> {
    data class Success<out T>(val data: T) : APIResult<T>()
    data class Failure(val error: String) : APIResult<Nothing>()
}
