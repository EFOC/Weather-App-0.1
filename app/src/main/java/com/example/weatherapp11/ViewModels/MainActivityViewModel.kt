package com.example.weatherapp11.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp11.Model.WeatherInfo
import com.example.weatherapp11.Repository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    var repo: Repository
    var list: LiveData<ArrayList<WeatherInfo>>

    init {
        list = MutableLiveData()
        repo = Repository(application)
    }

    fun getAllWeather(city: String): LiveData<ArrayList<WeatherInfo>> {
        list = repo.getWeather(city)
        return list
    }
}