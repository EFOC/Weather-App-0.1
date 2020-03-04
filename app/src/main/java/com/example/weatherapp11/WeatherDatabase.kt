package com.example.weatherapp11

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [WeatherEntity::class], version = 1)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
       private var instance: WeatherDatabase? = null
        @Synchronized
        fun getInstance(context: Context): WeatherDatabase? {
            if (instance != null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}
