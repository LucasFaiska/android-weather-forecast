package com.lfaiska.weather.domain.mapper

import com.lfaiska.weather.data.remote.dto.WeatherLocalResponse
import com.lfaiska.weather.domain.model.Weather
import com.lfaiska.weather.domain.model.WeatherLocal

class WeatherLocalMapper {
    companion object {
        fun WeatherLocalResponse.toWeatherLocal(): WeatherLocal {
            return WeatherLocal(
                this.title,
                this.colidatedWeather.map { consolidatedWeather ->
                    Weather(
                        consolidatedWeather.weatherStateAbbr,
                        consolidatedWeather.applicableDate,
                        consolidatedWeather.minTemperature,
                        consolidatedWeather.maxTemperature,
                        consolidatedWeather.windSpeed,
                        consolidatedWeather.humidity
                    )
                }
            )
        }
    }
}