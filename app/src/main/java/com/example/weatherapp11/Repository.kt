package com.example.weatherapp11

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp11.Model.Weather
import com.example.weatherapp11.Model.WeatherInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {

    var jsonWeatherApi: JsonWeatherApi
    lateinit var cities: Call<WeatherInfo>
    var list: MutableLiveData<ArrayList<WeatherInfo?>>
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        jsonWeatherApi = retrofit.create(JsonWeatherApi::class.java)
        list = MutableLiveData()
    }

    fun loadWeather(){
         cities = jsonWeatherApi.getWeatherInfo("toronto", "65c8bbb29469fa0f101001642a325d13")
    }

    fun getWeather(): MutableLiveData<ArrayList<WeatherInfo?>> {
        val allWeatherInfo: ArrayList<WeatherInfo?> = ArrayList()
        var weather: WeatherInfo? = null
        cities = jsonWeatherApi.getWeatherInfo("toronto", "65c8bbb29469fa0f101001642a325d13")
        cities.enqueue(object : Callback<WeatherInfo> {

            override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                Log.d("TEST", "Code: " + t.toString())
            }

            override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                if (response!!.isSuccessful) {
                    weather = response.body()
                    allWeatherInfo.add(weather)
                    Log.d("TEST", "Repo, Feels like: " + response.body()?.mainInfo?.feelsLike)
                    list.value = allWeatherInfo
                }
            }
        })
        Log.d("TEST", "repo: " + weather?.mainInfo?.feelsLike)
        return list
    }
}