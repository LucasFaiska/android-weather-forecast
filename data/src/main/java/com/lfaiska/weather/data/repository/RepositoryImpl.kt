package com.lfaiska.weather.data.repository

import com.lfaiska.weather.data.remote.Service
import com.lfaiska.weather.data.remote.dto.WeatherLocationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryImpl(private val service: Service) : Repository {
    override suspend fun getWeatherFromLocation(locationId: String): WeatherLocationResponse {
        return withContext(Dispatchers.IO) {
            try {
                service.getWeatherFromLocation(locationId)
            } catch (exception: Exception) {
                throw RepositoryException(exception)
            }
        }
    }
}