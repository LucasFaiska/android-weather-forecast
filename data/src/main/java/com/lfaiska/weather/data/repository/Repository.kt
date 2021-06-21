package com.lfaiska.weather.data.repository

import com.lfaiska.weather.data.remote.dto.WeatherLocalResponse

interface Repository {
    suspend fun getWeatherFromLocation(locationId: String): WeatherLocalResponse
}