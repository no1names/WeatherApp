package com.example.weatherapp.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.view.MainActivity
import com.example.weatherapp.view.fragments.listeners.SearchFragmentListener

class SearchFragment : Fragment(), View.OnClickListener {

    private lateinit var listener: SearchFragmentListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SearchFragmentListener) {
            listener = context
        } else {
            throw RuntimeException("Required Listener")
        }
    }

    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSearchBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewsListener()
        setToolbar()
    }

    private fun setToolbar() {
        (activity as MainActivity).setToolbar()
    }

    private fun setViewsListener() {
        setOnClicker()
    }

    private fun setOnClicker() {
        binding.btnSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == binding.btnSubmit) {
            listener.toListFragment(binding.etSearch.text.toString())
        }
    }

}