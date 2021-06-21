package com.lfaiska.weather.presentation.mapper

import com.lfaiska.weather.data.BuildConfig
import com.lfaiska.weather.domain.model.WeatherLocal
import com.lfaiska.weather.presentation.model.WeatherLocalPresentationModel
import com.lfaiska.weather.presentation.model.WeatherPresentationModel
import java.text.SimpleDateFormat
import java.util.*

class WeatherLocalMapper {
    companion object {
        fun WeatherLocal.toWeatherLocalPresentationModel(): WeatherLocalPresentationModel {
            val simpleDateFormat = SimpleDateFormat("EEEE", Locale.getDefault())

            return WeatherLocalPresentationModel(
                this.location,
                this.weatherList.map { weather ->
                    WeatherPresentationModel(
                        "https://www.metaweather.com/static/img/weather/png/${weather.weatherStateAbbreviation}.png",
                        simpleDateFormat.format(weather.date),
                        "${weather.minTemperature.toInt()}°/${weather.maxTemperature.toInt()}°",
                        "${weather.windSpeed.toInt()} mph",
                        "${weather.humidity}%"
                    )
                }
            )
        }
    }
}