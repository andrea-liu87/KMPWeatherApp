package com.andreasgift.kmpweatherapp.android

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {
    @Provides
    @Singleton
    @Named("weather_repo")
    fun provideWeatherRepoImpl(): WeatherRepositoryImpl =
        WeatherRepositoryImpl()

    @Provides
    @Singleton
    fun provideWeatherRepo(weatherRepo: WeatherRepositoryImpl): WeatherRepository =
        weatherRepo
}