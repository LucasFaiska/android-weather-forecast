package com.lfaiska.weather.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class ConsolidatedWeatherResponse (
    @SerializedName("weather_state_abbr")
    val weatherStateAbbr: String,
    @SerializedName("applicable_date")
    val applicableDate: Date,
    @SerializedName("min_temp")
    val minTemperature: Double,
    @SerializedName("max_temp")
    val maxTemperature: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("humidity")
    val humidity: Double
)
