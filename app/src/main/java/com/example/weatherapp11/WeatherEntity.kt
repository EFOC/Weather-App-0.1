package com.example.weatherapp11

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
class WeatherEntity(@PrimaryKey val nameOfCity: String)