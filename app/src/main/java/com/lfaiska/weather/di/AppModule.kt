package com.lfaiska.weather.di

import com.lfaiska.weather.data.repository.Repository
import com.lfaiska.weather.domain.usecase.GetWeatherFromLocationUseCase
import com.lfaiska.weather.domain.usecase.GetWeatherFromLocationUseCaseImpl
import org.koin.dsl.module

val appModule = module {
    single { provideGetWeatherFromLocationUseCase(get()) }
}

fun provideGetWeatherFromLocationUseCase(repository: Repository) : GetWeatherFromLocationUseCase {
    return GetWeatherFromLocationUseCaseImpl(repository)
}