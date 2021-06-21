package com.lfaiska.weather.data.repository

import com.lfaiska.weather.data.remote.dto.WeatherLocationResponse

interface Repository {
    suspend fun getWeatherFromLocation(locationId: String): WeatherLocationResponse
}