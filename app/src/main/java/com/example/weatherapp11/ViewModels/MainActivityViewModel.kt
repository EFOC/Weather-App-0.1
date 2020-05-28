package com.example.weatherapp11.ViewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.weatherapp11.Model.MainWeatherInfo
import com.example.weatherapp11.Model.WeatherInfo
import com.example.weatherapp11.Repository
import com.example.weatherapp11.WeatherDatabase
import com.example.weatherapp11.WeatherEntity
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var repo: Repository
    var weatherList: ArrayList<LiveData<WeatherInfo>>

    init {
        val weatherDao = WeatherDatabase.getInstance(application, viewModelScope)!!.weatherDao()
        repo = Repository(weatherDao)
        weatherList = ArrayList()
    }

    fun getWeather(city: String): LiveData<WeatherInfo> {
        return repo.getWeather(city)
    }

    fun getAll(): LiveData<List<String>> {
        return repo.getAll()
    }

    // @TODO Insert only name of city
    fun insert(city: String) {
        viewModelScope.launch {
            repo.insert(city)
        }
    }
}