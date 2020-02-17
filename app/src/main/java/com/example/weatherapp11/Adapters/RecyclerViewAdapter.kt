package com.example.weatherapp11.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp11.Model.WeatherInfo
import com.example.weatherapp11.R

class RecyclerViewAdapter(var context: Context, var weatherList: List<WeatherInfo>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cityName.text = "Toronto"
        holder.feelsLike.text = weatherList[position].mainInfo.feelsLike.toString()
    }

    class ViewHolder(itemHolder: View): RecyclerView.ViewHolder(itemHolder) {
        var cityName = itemHolder.findViewById<TextView>(R.id.item_city_name)
        var feelsLike = itemHolder.findViewById<TextView>(R.id.item_feels_like)
    }
}