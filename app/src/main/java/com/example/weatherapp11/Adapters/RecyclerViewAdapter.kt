package com.example.weatherapp11.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp11.Model.Weather
import com.example.weatherapp11.Model.WeatherInfo
import com.example.weatherapp11.R
import org.w3c.dom.Text

class RecyclerViewAdapter(var context: Context) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var weatherList: ArrayList<WeatherInfo>

    init {
        weatherList = ArrayList()
    }

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

    fun setWeather(list: ArrayList<WeatherInfo>){
        weatherList = list
    }

    class ViewHolder(itemHolder: View): RecyclerView.ViewHolder(itemHolder) {
        var cityName = itemHolder.findViewById<TextView>(R.id.item_city_name)
        var feelsLike = itemHolder.findViewById<TextView>(R.id.item_feels_like)
        var maxTemp = itemHolder.findViewById<TextView>(R.id.item_max_temp)
        var minTemp = itemHolder.findViewById<TextView>(R.id.item_min_temp)
    }
}