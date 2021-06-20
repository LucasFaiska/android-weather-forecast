package com.lfaiska.weather.data.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.lfaiska.weather.data.BuildConfig

val dataModule = module {
    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) } }

fun provideGson(): Gson {
    return GsonBuilder().setLenient().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
}

fun provideHttpClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
    return okHttpClientBuilder.build()
}

fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(factory))
        .client(client)
        .build()
}