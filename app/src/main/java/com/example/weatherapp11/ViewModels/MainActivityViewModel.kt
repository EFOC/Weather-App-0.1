package com.example.weatherapp11.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp11.Model.WeatherInfo
import com.example.weatherapp11.Repository

class MainActivityViewModel: ViewModel() {
    var repo: Repository
    var list: MutableLiveData<ArrayList<WeatherInfo?>>

    init {
        list = MutableLiveData()
        repo = Repository
    }

    fun getAllWeather(city: String): MutableLiveData<ArrayList<WeatherInfo?>> {
        list = repo.getWeather(city)
        return list
    }
}