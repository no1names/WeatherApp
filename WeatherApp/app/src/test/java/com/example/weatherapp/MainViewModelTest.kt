package com.example.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.weatherapp.model.WeatherRepo
import com.example.weatherapp.model.dataclasses.WeatherResponse
import com.example.weatherapp.utils.APIResult
import com.example.weatherapp.utils.Constants.ERROR_RESPONSE_TEXT
import com.example.weatherapp.utils.Constants.SAMPLE_CITY_NAME
import com.example.weatherapp.viewModel.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var weatherRepo: WeatherRepo

    @RelaxedMockK
    lateinit var observer: Observer<APIResult<WeatherResponse>>
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(weatherRepo)
        viewModel.weatherAPIResult.observeForever(observer)
    }

    @Test
    fun testExistedObserver() {
        Assert.assertTrue(viewModel.weatherAPIResult.hasObservers())
    }

    @Test
    fun testSuccessAPiResult() {
        every {
            weatherRepo.getForestWeatherDailyListResponse(any())
        } returns flow {
            emit(APIResult.Success(WeatherResponse(listOf())))
        }

        viewModel.fetchWeatherResponse(SAMPLE_CITY_NAME)

        verify {
            observer.onChanged(APIResult.Success(WeatherResponse(listOf())))
        }
    }

    @Test
    fun testFailureAPiResult() {
        every {
            weatherRepo.getForestWeatherDailyListResponse(any())
        } returns flow {
            emit(APIResult.Failure(ERROR_RESPONSE_TEXT))
        }

        viewModel.fetchWeatherResponse("")

        verify {
            observer.onChanged(APIResult.Failure(ERROR_RESPONSE_TEXT))
        }
    }
}