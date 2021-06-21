package com.lfaiska.weather.data.remote

import com.lfaiska.weather.data.remote.dto.WeatherLocationResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {
    @GET("location/{locationId}")
    suspend fun getWeatherFromLocation(@Path("locationId") locationId: String): WeatherLocationResponse
}