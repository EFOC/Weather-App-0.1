package com.example.weatherapp11.viewModels

import android.app.Application
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.*
import com.example.weatherapp11.models.WeatherInfo
import com.example.weatherapp11.Repository
import com.example.weatherapp11.database.WeatherDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var repo: Repository
    lateinit var progressSpinner: ProgressBar

    init {
        val weatherDao = WeatherDatabase.getInstance(application, viewModelScope)!!.weatherDao()
        repo = Repository(weatherDao)
    }

    private fun getWeather(cities: List<String>): LiveData<ArrayList<WeatherInfo>> {
        return repo.getWeather(cities)
    }

    fun refreshList() {
        viewModelScope.launch {
            delay(5_000)
            progressSpinner.visibility = View.GONE
        }
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