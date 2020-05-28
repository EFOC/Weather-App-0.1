package com.example.weatherapp11

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp11.Model.Weather
import com.example.weatherapp11.Model.WeatherInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository(var weatherDao: WeatherDao) {

    private var jsonWeatherApi: JsonWeatherApi
    lateinit var cities: Call<WeatherInfo>

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jsonWeatherApi = retrofit.create(JsonWeatherApi::class.java)
    }

    fun getWeather(city: String): LiveData<WeatherInfo> {
        cities = jsonWeatherApi.getWeatherInfo(city, "65c8bbb29469fa0f101001642a325d13")
        val liveWeather: MutableLiveData<WeatherInfo> = MutableLiveData()
        cities.enqueue(object : Callback<WeatherInfo> {
            override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                Log.d("TEST", "Code: " + t.toString())
            }

            override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                if (response!!.isSuccessful) {
                    liveWeather.value = response.body()
                }
            }
        })
        return liveWeather
    }

    suspend fun insert(city: String) {
        weatherDao.insert(WeatherEntity(city))
    }

    fun getAll(): LiveData<List<String>> {
        return weatherDao.getAllWeather()
    }
}