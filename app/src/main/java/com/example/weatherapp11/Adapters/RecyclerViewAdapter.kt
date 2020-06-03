package com.example.weatherapp11.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp11.Model.WeatherInfo
import com.example.weatherapp11.R

class RecyclerViewAdapter(var context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private lateinit var weatherList: List<WeatherInfo>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cityName.text = "City Name: " + weatherList.get(position)?.nameOfCity.toString()
        holder.feelsLike.text = "Feels Like: " + weatherList.get(position)?.mainInfo?.maxTemp.toString()
        holder.maxTemp.text = "Max Temp: " + weatherList.get(position)?.mainInfo?.maxTemp.toString()
        holder.minTemp.text = "Min Temp: " + weatherList.get(position)?.mainInfo?.minTemp.toString()
    }

    fun setWeather(list: List<WeatherInfo>){
        weatherList = list
    }

    class ViewHolder(itemHolder: View): RecyclerView.ViewHolder(itemHolder) {
        var cityName: TextView = itemHolder.findViewById(R.id.item_city_name)
        var feelsLike: TextView = itemHolder.findViewById(R.id.item_feels_like)
        var maxTemp: TextView = itemHolder.findViewById(R.id.item_max_temp)
        var minTemp: TextView = itemHolder.findViewById(R.id.item_min_temp)
    }
}