package com.example.weatherapp11

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp11.Adapters.RecyclerViewAdapter
import com.example.weatherapp11.Model.WeatherInfo
import com.example.weatherapp11.ViewModels.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)
        mainActivityViewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        initRecyclerView()

        val weatherRetrieved: LiveData<WeatherInfo> = mainActivityViewModel.getWeather("toronto")
        weatherRetrieved.observe(this,
            Observer {
                mainActivityViewModel.weatherList.add(weatherRetrieved)
                adapter.setWeather(mainActivityViewModel.weatherList)
                recyclerView.adapter = adapter
            })

        btn = findViewById(R.id.btn)
        btn.setOnClickListener {
            mainActivityViewModel.getAll().observe(this, Observer { list ->
                list.forEach {
                    Log.d("TEST", "getting weather of: " + it.nameOfCity)
                }
            })
        }
    }

    private fun initRecyclerView() {
        adapter = RecyclerViewAdapter(this)
        val linearLayout = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayout
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_add_item) {
            val intent = Intent(this, AddWeatherItemActivity::class.java)
            startActivityForResult(intent, 1)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            val city = data.getStringExtra("city")
            val weatherRetrieved: LiveData<WeatherInfo> = mainActivityViewModel.getWeather(city)
            weatherRetrieved.observe(this@MainActivity,
                Observer {
                    mainActivityViewModel.weatherList.add(weatherRetrieved)
                    adapter.setWeather(mainActivityViewModel.weatherList)
                    recyclerView.adapter = adapter
                })
        }
    }
}