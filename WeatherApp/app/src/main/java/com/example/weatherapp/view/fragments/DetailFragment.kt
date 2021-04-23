package com.example.weatherapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.databinding.FragmentDetailBinding
import com.example.weatherapp.view.MainActivity

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val argument by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setToolbar()
    }

    private fun setToolbar() {
        (activity as MainActivity).setToolbar(argument.city, true)
    }

    private fun setViews() {
        val weatherInfo = argument.weatherInfo
        binding.txDegree.text = weatherInfo.main.temp.toString()
        binding.txFeelDegree.append(weatherInfo.main.feelsLike.toString())
        binding.txWeatherStatus.text = weatherInfo.weather.firstOrNull()?.main ?: ""
        binding.txWeatherDescription.text = weatherInfo.weather.firstOrNull()?.description ?: ""

    }

}