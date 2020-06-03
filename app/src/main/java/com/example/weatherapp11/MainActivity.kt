package com.example.weatherapp11

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
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

        mainActivityViewModel.getWeather(listOf("Toronto", "New York")).observe(this, Observer {
            adapter.setWeather(it)
            recyclerView.adapter = adapter
        })

        btn = findViewById(R.id.btn)
        btn.setOnClickListener {
            refreshWeatherList()
        }
    }

    private fun initRecyclerView() {
        adapter = RecyclerViewAdapter(this)
        val linearLayout = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayout
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Log.d("TEST", "item selected ${adapter.getItemAt(viewHolder.adapterPosition).nameOfCity} ")
                mainActivityViewModel.delete(WeatherEntity(adapter.getItemAt(viewHolder.adapterPosition).nameOfCity))
                Toast.makeText(this@MainActivity, "Deleting item...", Toast.LENGTH_SHORT).show()
//                refreshWeatherList()
            }

        }).attachToRecyclerView(recyclerView)
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
            mainActivityViewModel.insert(data.getStringExtra("city"))
            refreshWeatherList()
        }
    }

    private fun refreshWeatherList() {
        // TODO: fix the deleting last item in database
        mainActivityViewModel.getAll().observe(this, Observer {cityList ->
            cityList.forEach {
                Log.d("TEST", "Items in database: ${it.nameOfCity}")
            }
            adapter.setWeather(cityList)
            recyclerView.adapter = adapter
        })
    }
}