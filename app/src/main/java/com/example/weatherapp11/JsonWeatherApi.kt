package com.example.weatherapp11

import com.example.weatherapp11.models.WeatherInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonWeatherApi {

    @GET("weather")
    fun getWeatherInfo(@Query("q") city: String, @Query("appid") id: String) : Call<WeatherInfo>
}