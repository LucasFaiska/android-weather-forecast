package com.lfaiska.weather.presentation.model

data class WeatherLocalPresentationModel(
    val location: String,
    val weatherList: List<WeatherPresentationModel>
)