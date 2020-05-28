package com.example.weatherapp11

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.weatherapp11.Model.WeatherInfo

@Dao
interface WeatherDao {

    @Insert
    suspend fun insert(weather: WeatherEntity)

    @Update
    suspend fun update(weather: WeatherEntity)

//    @Delete
//    suspend fun delete(weather: WeatherInfo)


    @Query("SELECT * FROM weather_table")
    fun getAllWeather(): LiveData<List<WeatherEntity>>

    @Query("DELETE FROM weather_table")
    suspend fun deleteAll()
}