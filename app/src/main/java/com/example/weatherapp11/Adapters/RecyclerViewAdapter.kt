package com.example.weatherapp11.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        holder.cityName.text = context.getString(R.string.recycler_adapter_city_name, weatherList[position].nameOfCity)
        holder.feelsLike.text = context.getString(R.string.recycler_adapter_feels_like, weatherList[position].mainInfo.feelsLike)
        holder.maxTemp.text = context.getString(R.string.recycler_adapter_max_temp, weatherList[position].mainInfo.minTemp)
        holder.minTemp.text = context.getString(R.string.recycler_adapter_min_temp, weatherList[position].mainInfo.maxTemp)
    }

    fun setWeather(list: List<WeatherInfo>){
        weatherList = list
    }

    fun getItemAt(position: Int): WeatherInfo {
        return weatherList[position]
    }

    class ViewHolder(itemHolder: View): RecyclerView.ViewHolder(itemHolder) {
        var cityName: TextView = itemHolder.findViewById(R.id.item_city_name)
        var feelsLike: TextView = itemHolder.findViewById(R.id.item_feels_like)
        var maxTemp: TextView = itemHolder.findViewById(R.id.item_max_temp)
        var minTemp: TextView = itemHolder.findViewById(R.id.item_min_temp)
    }
}