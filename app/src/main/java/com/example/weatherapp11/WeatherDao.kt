package com.example.weatherapp11

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WeatherDao {

    @Insert
    suspend fun insert(city: WeatherEntity)

    @Update
    suspend fun update(city: WeatherEntity)

//    @Delete
//    suspend fun delete(weather: WeatherInfo)

    @Query("SELECT * FROM weather_table")
    fun getAllWeather(): LiveData<List<String>>

    @Query("DELETE FROM weather_table")
    suspend fun deleteAll()
}