package com.lfaiska.weather.presentation.scene.weather.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfaiska.weather.data.repository.RepositoryException
import com.lfaiska.weather.domain.usecase.GetWeatherFromLocationUseCase
import com.lfaiska.weather.presentation.mapper.WeatherLocalMapper.Companion.toWeatherLocalPresentationModel
import com.lfaiska.weather.presentation.model.WeatherLocalPresentationModel
import kotlinx.coroutines.launch

class WeatherListViewModel constructor(
    private val getWeatherFromLocationUseCase: GetWeatherFromLocationUseCase
) : ViewModel() {

    private val _weatherLocal = MutableLiveData<WeatherLocalPresentationModel>()
    val weatherLocal: LiveData<WeatherLocalPresentationModel> = _weatherLocal

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isEmpty = MutableLiveData(false)
    val isEmpty: LiveData<Boolean> = _isEmpty

    private val _hasLoadFailure = MutableLiveData(false)
    val hasLoadFailure: LiveData<Boolean> = _hasLoadFailure

    fun loadWeather() {
        viewModelScope.launch {
            _isLoading.value = true
            performLoadWeather()
            _isLoading.value = false
        }
    }

    private suspend fun performLoadWeather() {
        try {
            val weatherLocal = getWeatherFromLocationUseCase.execute("44418")
            _weatherLocal.value = weatherLocal.toWeatherLocalPresentationModel()
            if (weatherLocal.weatherList.isEmpty()) {
                _isEmpty.value = true
            }
        } catch (repositoryException: RepositoryException) {
            _hasLoadFailure.value = true
        }
    }
}