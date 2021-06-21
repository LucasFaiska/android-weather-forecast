package com.lfaiska.weather.domain.model

data class WeatherLocal(
    val location: String,
    val weatherList: List<Weather>
)