package com.lfaiska.weather.presentation.scene.weather.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lfaiska.weather.R
import com.lfaiska.weather.databinding.FragmentWeatherListBinding
import org.koin.androidx.viewmodel.compat.ViewModelCompat

class WeatherListFragment : Fragment() {
    private lateinit var binding: FragmentWeatherListBinding

    private val weatherListViewModel: WeatherListViewModel by ViewModelCompat.viewModel(
        this,
        WeatherListViewModel::class.java
    )

    private val weatherListAdapter: WeatherListAdapter =
        WeatherListAdapter { weather ->
            Toast.makeText(
                this.requireActivity(),
                weather.weekDay,
                Toast.LENGTH_LONG
            ).show()
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_weather_list, container, false)
        setupView()
        setupObservers()
        loadWeather()
        return binding.root
    }

    private fun setupView() {
        binding.viewmodel = weatherListViewModel
        binding.weatherList.apply {
            this.adapter = weatherListAdapter
            this.layoutManager = LinearLayoutManager(context)
        }

        binding.swipeContainer.setOnRefreshListener {
            refreshWeather()
        }
    }

    private fun setupObservers() {
        weatherListViewModel.weatherLocal.observe(viewLifecycleOwner, { weatherLocal ->
            binding.weatherLocal = weatherLocal
            weatherListAdapter.updateWeatherList(weatherLocal.weatherList)
        })

        weatherListViewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            binding.isLoading = isLoading
        })

        weatherListViewModel.isEmpty.observe(viewLifecycleOwner, { isEmpty ->
            binding.isEmpty = isEmpty
        })

        weatherListViewModel.hasLoadFailure.observe(viewLifecycleOwner, { hasFailure ->
            binding.hasFailure = hasFailure
        })
    }

    private fun loadWeather() {
        weatherListViewModel.loadWeather()
    }

    private fun refreshWeather() {
        weatherListViewModel.loadWeather()
        binding.swipeContainer.isRefreshing = false
    }
}