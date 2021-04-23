package com.example.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapp.model.WeatherRepo
import com.example.weatherapp.model.dataclasses.WeatherResponse
import com.example.weatherapp.model.remote.WeatherService
import com.example.weatherapp.utils.APIResult
import com.example.weatherapp.utils.Constants.SAMPLE_CITY_NAME
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class WeatherRepoTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var weatherService: WeatherService

    private lateinit var weatherRepo: WeatherRepo

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        weatherRepo = WeatherRepo(weatherService)
    }

    @Test
    fun testSuccessResponse() {
        runBlocking {
            coEvery {
                weatherService.getForestWeatherDailyListResponse(
                    SAMPLE_CITY_NAME,
                    any(),
                    "imperial"
                )
            } returns Response.success(WeatherResponse(listOf()))

            val result = weatherRepo.getForestWeatherDailyListResponse(SAMPLE_CITY_NAME).first()

            assertTrue(result is APIResult.Success)
        }
    }

    @Test
    fun testFailureResponse() {
        runBlocking {
            val errorResponse = ""
            val errorResponseBody =
                errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
            coEvery {
                weatherService.getForestWeatherDailyListResponse(
                    SAMPLE_CITY_NAME,
                    any(),
                    "imperial"
                )
            } returns Response.error(404, errorResponseBody)

            val result = weatherRepo.getForestWeatherDailyListResponse(SAMPLE_CITY_NAME).first()

            assertTrue(result is APIResult.Failure)

        }
    }
}