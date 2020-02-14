package com.example.weatherapp11.Model

import com.google.gson.annotations.SerializedName

class WeatherInfo {

    @SerializedName("coord")
    lateinit var coordinates: Coordinates

    @SerializedName("weather")
    lateinit var weather: Weather

    @SerializedName("main")
    lateinit var mainInfo: MainWeatherInfo

    @SerializedName("cod")
    var code: Int = 0
}