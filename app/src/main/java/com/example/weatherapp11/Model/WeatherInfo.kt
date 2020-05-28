package com.example.weatherapp11.Model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class WeatherInfo {

    @SerializedName("coord")
    lateinit var coordinates: Coordinates

    @SerializedName("weather")
    lateinit var weather: List<Weather>

    @SerializedName("main")
    lateinit var mainInfo: MainWeatherInfo

    @PrimaryKey
    @SerializedName("name")
    var nameOfCity: String = "TEST"

    @SerializedName("cod")
    var code: Int = 0
}