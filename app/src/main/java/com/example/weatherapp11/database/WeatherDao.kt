package com.example.weatherapp11.database

import androidx.room.*
import com.example.weatherapp11.WeatherEntity

@Dao
interface WeatherDao {

    @Insert
    suspend fun insert(city: WeatherEntity)

    @Update
    suspend fun update(city: WeatherEntity)

    @Delete
    suspend fun delete(city: WeatherEntity)

    @Query("SELECT * FROM weather_table")
    suspend fun getAll(): List<String>

    @Query("DELETE FROM weather_table")
    suspend fun deleteAll()
}