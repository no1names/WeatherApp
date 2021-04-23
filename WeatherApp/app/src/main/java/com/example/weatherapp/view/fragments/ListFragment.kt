package com.example.weatherapp.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.adapter.WeatherAdapter
import com.example.weatherapp.databinding.FragmentListBinding
import com.example.weatherapp.model.dataclasses.WeatherInfo
import com.example.weatherapp.utils.APIResult
import com.example.weatherapp.utils.setErrorMessage
import com.example.weatherapp.view.MainActivity
import com.example.weatherapp.view.fragments.listeners.ListFragmentListener
import com.example.weatherapp.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var listener: ListFragmentListener
    private val argument by navArgs<ListFragmentArgs>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ListFragmentListener) {
            listener = context
        } else {
            throw RuntimeException("Required Fragment Listener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentListBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        viewModel.fetchWeatherResponse(argument.city)
        setToolbar()
    }

    private fun setToolbar() {
        (activity as MainActivity).setToolbar(argument.city, true)
    }

    private fun setObserver() {
        viewModel.weatherAPIResult.observe(viewLifecycleOwner) {
            when (it) {
                is APIResult.Success -> {
                    setRecycleView(it.data.list)
                }
                is APIResult.Failure -> {
                    setRecycleView(listOf())
                    binding.txError.setErrorMessage(it.error)
                }
            }
        }
    }

    private fun setRecycleView(list: List<WeatherInfo>) {
        val manager = LinearLayoutManager(context)
        val adapter = WeatherAdapter(list) {
            listener.toDetailFragment(it, argument.city)
        }
        binding.rvWeathers.adapter = adapter
        binding.rvWeathers.layoutManager = manager
    }

}