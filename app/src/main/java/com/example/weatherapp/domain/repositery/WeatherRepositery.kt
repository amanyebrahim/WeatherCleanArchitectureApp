package com.example.weatherapp.domain.repositery

import com.example.weatherapp.domain.util.Resource
import com.example.weatherapp.domain.weather.WeatherInfo

interface WeatherRepositery {
    suspend fun getWeatherInfo(lat:Double,lng:Double):Resource<WeatherInfo>
}