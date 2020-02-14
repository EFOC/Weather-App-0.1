package com.example.weatherapp11.Model

import com.google.gson.annotations.SerializedName

class MainWeatherInfo {

    @SerializedName("temp")
    var temperature: Float = 0.0F

    @SerializedName("feels_like")
    var feelsLike: Float = 0.0F

    @SerializedName("temp_min")
    var minTemp: Float = 0.0F

    @SerializedName("temp_max")
    var maxTemp: Float = 0.0F

    @SerializedName("pressure")
    var pressure: Float = 0.0F

    @SerializedName("humidity")
    var humidity: Float = 0.0F
}