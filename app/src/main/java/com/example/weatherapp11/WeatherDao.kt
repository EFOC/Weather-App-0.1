package com.example.weatherapp11

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.weatherapp11.Model.WeatherInfo

interface WeatherDao {

    @Insert
    fun insert(weather: WeatherInfo)

    @Update
    fun update(weather: WeatherInfo)

    @Delete
    fun delete(weather: WeatherInfo)


}