package com.example.weatherapp11

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp11.Adapters.RecyclerViewAdapter
import com.example.weatherapp11.ViewModels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var btn: Button
    private lateinit var progressSpinner: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)
        mainActivityViewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        spinnerInit()
        initRecyclerView()

        btn = findViewById(R.id.btn)
        btn.setOnClickListener {
            refreshWeatherList()
        }
    }

    private fun spinnerInit() {
        progressSpinner = findViewById(R.id.progressBar)
        progressSpinner.visibility = View.GONE
        mainActivityViewModel.progressSpinner = progressSpinner
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
                mainActivityViewModel.delete(adapter.getItemAt(viewHolder.adapterPosition).nameOfCity)
                Toast.makeText(this@MainActivity, getString(R.string.main_activity_delete_item, adapter.getItemAt(viewHolder.adapterPosition).nameOfCity), Toast.LENGTH_SHORT).show()
                refreshWeatherList()
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
        } else if (item?.itemId == R.id.menu_delete_all) {
            mainActivityViewModel.deleteAll()
            Toast.makeText(this, getString(R.string.main_activity_delete_all), Toast.LENGTH_SHORT).show()
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
        mainActivityViewModel.getAll().observe(this, Observer {cityList ->
            progressSpinner.visibility = View.GONE
            adapter.setWeather(cityList)
            recyclerView.adapter = adapter
        })
        progressSpinner.visibility = View.VISIBLE
        adapter.setWeather(ArrayList())
        recyclerView.adapter = adapter
        mainActivityViewModel.refreshList()
    }
}