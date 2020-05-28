package com.example.weatherapp11

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [WeatherEntity::class], version = 2, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        @Synchronized
        fun getInstance(context: Context, scope: CoroutineScope): WeatherDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_database")
                    .addCallback(WeatherDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE
        }
    }

    private class WeatherDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { weatherDatabase ->
                scope.launch {
                    populateDatabase(weatherDatabase.weatherDao())
                }
            }
        }

        suspend fun populateDatabase(weatherDao: WeatherDao) {
            weatherDao.deleteAll()
            weatherDao.insert(WeatherEntity("Vaughan"))
            weatherDao.insert(WeatherEntity("New York"))
        }
    }
}