package com.example.weatherapp.di

import com.example.weatherapp.data.repositery.WeatherRepositeryImple
import com.example.weatherapp.domain.repositery.WeatherRepositery
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositeryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepositery(
        weatherRepositeryImple: WeatherRepositeryImple
    ):WeatherRepositery
}