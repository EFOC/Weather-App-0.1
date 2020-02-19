package com.example.weatherapp11

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp11.Model.WeatherInfo

class MainActivityViewModel: ViewModel() {
    var repo: Repository
    var list: MutableLiveData<ArrayList<WeatherInfo?>>

    init {
        list = MutableLiveData()
        repo = Repository
    }

    fun getAllWeather(): MutableLiveData<ArrayList<WeatherInfo?>> {
        list = repo.getWeather()
        return list
    }

    fun selectedItemOption(context: Context, item: MenuItem?) {
        if (item?.itemId == R.id.menu_add_item) {
            val intent = Intent(context, AddWeatherItemActivity::class.java)
            context.startActivity(intent)
        }
    }
}