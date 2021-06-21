package com.lfaiska.weather.domain.model

import java.util.*

data class Weather(
    val weatherStateAbbreviation: String,
    val date: Date,
    val minTemperature: Double,
    val maxTemperature: Double,
    val windSpeed: Double,
    val humidity: Int
)