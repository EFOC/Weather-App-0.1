package com.example.weatherapp11

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp11.Model.Coordinates
import com.example.weatherapp11.Model.MainWeatherInfo
import com.example.weatherapp11.Model.Weather

@Entity(tableName = "weather_table")
class WeatherEntity(var nameOfCity: String,
var feelsLike: Float,
var maxTemp: Float,
var minTemp: Float) {

    @PrimaryKey(autoGenerate = true)
    lateinit var id: Integer

//    lateinit var nameOfCity: String

//    var feelsLike: Float = 99F

//    var maxTemp: Float = 99F
//
//    var minTemp: Float = 99F

//    var coordinates: Coordinates = Coordinates()
//
//    var mainInfo: MainWeatherInfo = MainWeatherInfo()
//
//    var weather: List<Weather> = List<Weather>
}