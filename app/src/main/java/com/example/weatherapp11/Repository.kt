package com.example.weatherapp11

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp11.apis.JsonWeatherApi
import com.example.weatherapp11.database.WeatherDao
import com.example.weatherapp11.database.WeatherEntity
import com.example.weatherapp11.models.WeatherInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository(private val weatherDao: WeatherDao) {

    private val WEATHER_API = BuildConfig.WeatherAPI
    private var jsonWeatherApi: JsonWeatherApi
    lateinit var cities: Call<WeatherInfo>
    private val liveDataList: MutableLiveData<ArrayList<WeatherInfo>> = MutableLiveData()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jsonWeatherApi = retrofit.create(JsonWeatherApi::class.java)
    }

    fun getWeather(cityList: List<String>): LiveData<ArrayList<WeatherInfo>> {
        val tempList: ArrayList<WeatherInfo> = ArrayList()
        if (cityList.isEmpty())
            return MutableLiveData()

        cityList.forEach {city ->
            cities = jsonWeatherApi.getWeatherInfo(city, WEATHER_API)
            cities.enqueue(object : Callback<WeatherInfo> {
                override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                    Log.d("Error", "Code: $t")
                }

                override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                    if (response!!.isSuccessful) {
                        tempList.add(response.body()!!)
                        liveDataList.value = tempList
                    }
                }
            })
        }
        return liveDataList
    }

    suspend fun insert(city: String) {
        weatherDao.insert(WeatherEntity(city))
    }

    suspend fun getAll(): List<String> {
        return weatherDao.getAll()
    }

    suspend fun delete(city: String) {
        weatherDao.delete(WeatherEntity(city))
    }

    suspend fun deleteAll() {
        weatherDao.deleteAll()
    }
}