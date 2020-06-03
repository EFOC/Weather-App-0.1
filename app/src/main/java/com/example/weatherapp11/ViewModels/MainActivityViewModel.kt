package com.example.weatherapp11.ViewModels

import android.app.Application
import androidx.lifecycle.*
import com.example.weatherapp11.Model.WeatherInfo
import com.example.weatherapp11.Repository
import com.example.weatherapp11.WeatherDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var repo: Repository

    init {
        val weatherDao = WeatherDatabase.getInstance(application, viewModelScope)!!.weatherDao()
        repo = Repository(weatherDao)
    }

    private fun getWeather(cities: List<String>): LiveData<ArrayList<WeatherInfo>> {
        return repo.getWeather(cities)
    }

    fun getAll(): LiveData<ArrayList<WeatherInfo>> = runBlocking {
        return@runBlocking getWeather(repo.getAll())
    }

    fun insert(city: String) {
        viewModelScope.launch {
            repo.insert(city)
        }
    }

    fun delete(city: String) {
        viewModelScope.launch {
            repo.delete(city)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repo.deleteAll()
        }
    }
}