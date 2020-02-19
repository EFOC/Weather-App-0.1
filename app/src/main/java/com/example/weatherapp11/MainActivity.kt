package com.example.weatherapp11

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp11.Adapters.RecyclerViewAdapter
import com.example.weatherapp11.Model.WeatherInfo


class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)
        mainActivityViewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        initRecyclerView()
        mainActivityViewModel.getAllWeather().observe(this,
            Observer<ArrayList<WeatherInfo?>> { list -> adapter.setWeather(list)
                recyclerView.adapter = adapter
                Log.d("TEST", "in observe: " + list[0]?.mainInfo?.feelsLike)})
    }

    fun initRecyclerView() {
        adapter = RecyclerViewAdapter(this)
        val linearLayout = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayout
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
}
