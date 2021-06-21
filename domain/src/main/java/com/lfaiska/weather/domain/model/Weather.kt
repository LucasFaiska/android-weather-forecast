package com.lfaiska.weather.domain.model

import com.lfaiska.weather.data.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

data class Weather(
    val weatherStateAbbreviation: String,
    val date: Date,
    val minTemperature: Double,
    val maxTemperature: Double,
    val windSpeed: Double,
    val humidity: Int
) {
    //@TODO maybe move this to another model closer than presenter because this rules are unique from presentation layer
    val icon: String
        get() = "${BuildConfig.BASE_URL}static/img/weather/png/$weatherStateAbbreviation.png"

    val weekDay: String
        get() {
            val simpleDateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
            return simpleDateFormat.format(date)
        }

    val temperatureFormatted: String
        get() = "${minTemperature.toInt()}°/${maxTemperature.toInt()}°"
}