package com.example.weatherapp11

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
class WeatherEntity {

    @PrimaryKey(autoGenerate = true)
    lateinit var id: Integer

    lateinit var nameOfCity: String

    var feelsLike: Float = 0F

    var maxTemp: Float = 0F

    var minTemp: Float = 0F

}