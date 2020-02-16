package com.example.weatherapp11

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp11.Model.WeatherInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val jsonWeatherApi: JsonWeatherApi = retrofit.create(JsonWeatherApi::class.java)

        val test: Call<WeatherInfo> = jsonWeatherApi.getWeatherInfo("toronto", "65c8bbb29469fa0f101001642a325d13")
        test.enqueue(object : Callback<WeatherInfo> {
            override fun onFailure(call: Call<WeatherInfo>?, t: Throwable?) {
                Log.d("TEST", "error: " + t.toString())
            }

            override fun onResponse(call: Call<WeatherInfo>?, response: Response<WeatherInfo>?) {
                Log.d("TEST", "code: " + response?.body()?.code + "\n" +
                        "Coordinates: " + response?.body()?.coordinates?.lat +
                        " and " + response?.body()?.coordinates?.lon + " " +
                        "\n description: " + response?.body()?.weather?.get(0)?.descrption + "" +
                        "\n max temp: " + response?.body()?.mainInfo?.maxTemp)
            }
        })
    }
}
