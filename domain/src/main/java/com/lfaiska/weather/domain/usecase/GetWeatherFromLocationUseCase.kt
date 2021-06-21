package com.lfaiska.weather.domain.usecase

import com.lfaiska.weather.domain.model.WeatherLocal

interface GetWeatherFromLocationUseCase {
    suspend fun execute(locationId: String) : WeatherLocal
}