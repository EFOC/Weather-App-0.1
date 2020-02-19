package com.example.weatherapp11

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp11.Model.WeatherInfo

class MainActivityViewModel: ViewModel() {
    var repo: Repository
    var list: MutableLiveData<ArrayList<WeatherInfo?>>

    fun getAllWeather(): MutableLiveData<ArrayList<WeatherInfo?>> {
        list = repo.getWeather()
        return list
    }


    init {
        list = MutableLiveData()
        repo = Repository
    }
}