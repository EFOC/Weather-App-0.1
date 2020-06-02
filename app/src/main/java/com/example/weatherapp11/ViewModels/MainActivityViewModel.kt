package com.example.weatherapp11.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp11.Model.WeatherInfo
import com.example.weatherapp11.Repository
import com.example.weatherapp11.WeatherDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var repo: Repository
    var weatherList: ArrayList<LiveData<WeatherInfo>>
    var mediatorLiveData = MediatorLiveData<List<Any>>()

    init {
        val weatherDao = WeatherDatabase.getInstance(application, viewModelScope)!!.weatherDao()
        repo = Repository(weatherDao)
        weatherList = ArrayList()
    }

    fun getWeather(cities: List<String>): LiveData<ArrayList<WeatherInfo>> {
        return repo.getWeather(cities)
    }

    fun getAll(): LiveData<ArrayList<WeatherInfo>> = runBlocking {
        return@runBlocking getWeather(repo.getAll())
    }

    // @TODO Insert only name of city
    fun insert(city: String) {
        viewModelScope.launch {
            repo.insert(city)
        }
    }
}