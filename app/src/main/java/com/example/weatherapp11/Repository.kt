package com.example.weatherapp11

import com.example.weatherapp11.Model.WeatherInfo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {

    var jsonWeatherApi: JsonWeatherApi
    lateinit var cities: Call<WeatherInfo>
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jsonWeatherApi = retrofit.create(JsonWeatherApi::class.java)
    }

    fun loadWeather(){
         cities = jsonWeatherApi.getWeatherInfo("toronto", "65c8bbb29469fa0f101001642a325d13")
    }

    fun getWeather(city: String){
        jsonWeatherApi.getWeatherInfo(city, "65c8bbb29469fa0f101001642a325d13")
    }
}