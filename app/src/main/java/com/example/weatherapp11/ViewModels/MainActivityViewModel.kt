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

    fun getAll(): LiveData<ArrayList<WeatherInfo>> {
        val repoList: LiveData<List<WeatherEntity>> = repo.getAll()
        val liveList: LiveData<ArrayList<WeatherInfo>>
        liveList = Transformations.map(repoList) { list ->
            val newList: ArrayList<WeatherInfo> = ArrayList()
            list?.forEach {
                val temp = WeatherInfo()
                temp.mainInfo = MainWeatherInfo()
                temp.nameOfCity = it.nameOfCity
                temp.mainInfo.feelsLike = it.feelsLike
                temp.mainInfo.maxTemp = it.maxTemp
                temp.mainInfo.minTemp = it.minTemp
                newList.add(temp)
            }
            return@map newList
        }
        return liveList
    }

    // @TODO Insert only name of city
    fun insert(weatherInfo: WeatherInfo) {
        viewModelScope.launch {
            val weatherEntity = WeatherEntity(
                weatherInfo.nameOfCity,
                weatherInfo.mainInfo.feelsLike,
                weatherInfo.mainInfo.maxTemp,
                weatherInfo.mainInfo.minTemp
            )
            repo.insert(weatherEntity)
        }
    }
}