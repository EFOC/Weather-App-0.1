package com.example.weatherapp11

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp11.Adapters.RecyclerViewAdapter
import com.example.weatherapp11.Model.WeatherInfo
import com.example.weatherapp11.ViewModels.MainActivityViewModel


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
//        mainActivityViewModel.getAllWeather().observe(this,
//            Observer<ArrayList<WeatherInfo?>> { list -> adapter.setWeather(list)
//                recyclerView.adapter = adapter })
    }

    fun initRecyclerView() {
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
            mainActivityViewModel.getAllWeather(data.getStringExtra("city")).observe(this,
                Observer<ArrayList<WeatherInfo?>> { list -> adapter.setWeather(list)
                    recyclerView.adapter = adapter })
        }
    }

}
