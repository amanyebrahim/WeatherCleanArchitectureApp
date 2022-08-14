package com.example.weatherapp.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.domain.repositery.WeatherRepositery
import com.example.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.jvm.internal.Intrinsics

@HiltViewModel
class WeatherViewModel@Inject constructor(
    private val repository: WeatherRepositery,
    private val locationTracker: LocationTracker
) :ViewModel() {

    var state by mutableStateOf(WeatherState())
    private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = WeatherState(
                isLoading = true,
                weatherInfo = null
            )

            locationTracker.getCurrentLocation()?.let {
           when(val result = repository.getWeatherInfo(it.latitude,it.longitude)){
               is Resource.Success ->{
                   state = WeatherState(
                       weatherInfo = result.data,
                       isLoading = false,
                       error = null
                   )
               }
                   is Resource.Error->{
                       state = WeatherState(
                           weatherInfo = null,
                           isLoading = false,
                           error = result.message
                       )
                   }
           }
            }?: run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }
        }
    }
}