package com.lfaiska.weather.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherPresentationModel(
    val icon: String,
    val weekDay: String,
    val temperature: String,
    val windSpeed: String,
    val humidity: String
) : Parcelable