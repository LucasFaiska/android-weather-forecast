package com.lfaiska.weather.di

import com.lfaiska.weather.data.repository.Repository
import com.lfaiska.weather.domain.usecase.GetWeatherFromLocationUseCase
import com.lfaiska.weather.domain.usecase.GetWeatherFromLocationUseCaseImpl
import com.lfaiska.weather.presentation.scene.splash.SplashViewModel
import com.lfaiska.weather.presentation.scene.weather.list.WeatherListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SplashViewModel() }
    viewModel { WeatherListViewModel(get()) }
    single { provideGetWeatherFromLocationUseCase(get()) }
}

fun provideGetWeatherFromLocationUseCase(repository: Repository) : GetWeatherFromLocationUseCase {
    return GetWeatherFromLocationUseCaseImpl(repository)
}