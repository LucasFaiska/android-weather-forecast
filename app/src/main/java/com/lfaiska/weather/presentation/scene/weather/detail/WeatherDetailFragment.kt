package com.lfaiska.weather.presentation.scene.weather.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.lfaiska.weather.R
import com.lfaiska.weather.databinding.FragmentWeatherDetailBinding

class WeatherDetailFragment : Fragment() {
    private lateinit var binding: FragmentWeatherDetailBinding
    private val arguments: WeatherDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_weather_detail,
            container,
            false
        )
        binding.weather = arguments.weather
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.toolbarBackButton.setOnClickListener {
            activity?.onBackPressed()
        }
    }
}