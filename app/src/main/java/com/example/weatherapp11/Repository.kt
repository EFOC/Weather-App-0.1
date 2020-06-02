package com.example.weatherapp11

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp11.Model.WeatherInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository(var weatherDao: WeatherDao) {

    private var jsonWeatherApi: JsonWeatherApi
    lateinit var cities: Call<WeatherInfo>
    val s: MutableLiveData<ArrayList<WeatherInfo>> = MutableLiveData()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jsonWeatherApi = retrofit.create(JsonWeatherApi::class.java)
    }

    fun getWeather(cityList: List<String>): LiveData<ArrayList<WeatherInfo>> {
        val t: ArrayList<WeatherInfo> = ArrayList()
        cityList.forEach {city ->
            cities = jsonWeatherApi.getWeatherInfo(city, "65c8bbb29469fa0f101001642a325d13")
            cities.enqueue(object : Callback<WeatherInfo> {
                override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                    Log.d("TEST", "Code: " + t.toString())
                }

                override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                    if (response!!.isSuccessful) {
                        t.add(response.body()!!)
                        s.value = t
                    }
                }
            })
        }
        return s
    }

    suspend fun insert(city: String) {
        weatherDao.insert(WeatherEntity(city))
    }

    suspend fun getAll(): List<String> {
        return weatherDao.getAllWeather()
    }
}