package com.lfaiska.weather.domain.usecase

import com.lfaiska.weather.data.repository.Repository
import com.lfaiska.weather.domain.mapper.WeatherLocalMapper.Companion.toWeatherLocal
import com.lfaiska.weather.domain.model.WeatherLocal

class GetWeatherFromLocationUseCaseImpl(
    private val repository: Repository
) : GetWeatherFromLocationUseCase {
    override suspend fun execute(locationId: String): WeatherLocal =
        repository.getWeatherFromLocation(locationId).toWeatherLocal()
}