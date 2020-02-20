package com.example.weatherapp11.Model

import com.google.gson.annotations.SerializedName

class WeatherInfo {

    @SerializedName("coord")
    lateinit var coordinates: Coordinates

    @SerializedName("weather")
    lateinit var weather: List<Weather>

    @SerializedName("main")
    lateinit var mainInfo: MainWeatherInfo

    @SerializedName("name")
    lateinit var nameOfCity: String

    @SerializedName("cod")
    var code: Int = 0
}