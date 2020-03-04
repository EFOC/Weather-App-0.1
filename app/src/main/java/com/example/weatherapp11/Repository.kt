package com.example.weatherapp11

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp11.Model.WeatherInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository(application: Application) {

    lateinit var jsonWeatherApi: JsonWeatherApi
    lateinit var cities: Call<WeatherInfo>
    var list: LiveData<ArrayList<WeatherInfo>> = MutableLiveData()
    var allWeatherInfo: LiveData<ArrayList<WeatherInfo>> = TODO()
    lateinit var weatherDoa: WeatherDao

    init {
        Log.d("TEST", "Creating repo")
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jsonWeatherApi = retrofit.create(JsonWeatherApi::class.java)
        val weatherDatabase = WeatherDatabase.getInstance(application)
        if (weatherDatabase != null) {
            weatherDoa = weatherDatabase.weatherDao()
            allWeatherInfo = weatherDoa.getAllWeather()
        }
    }

    fun getWeather(city: String): LiveData<ArrayList<WeatherInfo>> {
        var weather: WeatherInfo? = null
        cities = jsonWeatherApi.getWeatherInfo(city, "65c8bbb29469fa0f101001642a325d13")
        cities.enqueue(object : Callback<WeatherInfo> {

            override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                Log.d("TEST", "Code: " + t.toString())
            }

            override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                if (response!!.isSuccessful) {
                    weather = response.body()
                    allWeatherInfo.value?.add(weather!!)
                    Log.d("TEST", "Repo, Feels like: " + response.body()?.mainInfo?.feelsLike)
                    list = allWeatherInfo
                }
            }
        })
        Log.d("TEST", "repo: " + weather?.mainInfo?.feelsLike)
        return list
    }

    fun insert(weatherInfo: WeatherInfo) {

    }

    fun delete(weatherInfo: WeatherInfo) {

    }

    fun update(weatherInfo: WeatherInfo) {

    }

    fun deleteAllInfo() {

    }

    fun getAllInfo(): LiveData<ArrayList<WeatherInfo>> {
        return allWeatherInfo
    }
}