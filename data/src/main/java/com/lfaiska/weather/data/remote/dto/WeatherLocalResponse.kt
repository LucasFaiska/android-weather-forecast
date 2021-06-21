package com.lfaiska.weather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherLocalResponse(
    @SerializedName("consolidated_weather")
    val colidatedWeather: List<ConsolidatedWeatherResponse>,
    val title: String
)
