package com.example.weatherapp11

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener


class AddWeatherItemActivity : AppCompatActivity() {

    private val API_KEY = "AIzaSyCI9kZvQL35PVVrVYtrNJChXS9itiH1k9s"
    private lateinit var completeButton: Button
    private lateinit var addWeatherItemViewModel: AddWeatherItemViewModel
    private var placeSelected: String = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_weather_item)

        googleAutocompleteInit()
        addWeatherItemViewModel = ViewModelProvider(this).get(AddWeatherItemViewModel::class.java)

        completeButton = findViewById(R.id.add_weather_done_btn)
        completeButton.setOnClickListener {
            val result = Intent()
            result.putExtra("city", placeSelected)
            setResult(Activity.RESULT_OK, result)
            finish()
        }
    }

    private fun googleAutocompleteInit() {
        Places.initialize(applicationContext, API_KEY)
        val autocompleteSupportFragment: AutocompleteSupportFragment = supportFragmentManager.findFragmentById(R.id.fragment_add_item) as AutocompleteSupportFragment
        autocompleteSupportFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME))
        autocompleteSupportFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                placeSelected = place.name!!
            }

            override fun onError(status: Status) {
                Log.d("Error", "Status: ${status.statusMessage}")
            }

        })
    }
}
