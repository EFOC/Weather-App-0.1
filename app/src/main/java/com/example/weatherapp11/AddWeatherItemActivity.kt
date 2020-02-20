package com.example.weatherapp11

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders

class AddWeatherItemActivity : AppCompatActivity() {

    lateinit var completeButton: Button
    lateinit var addWeatherItemViewModel: AddWeatherItemViewModel
    lateinit var cityNameEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_weather_item)
        addWeatherItemViewModel = ViewModelProviders.of(this).get(AddWeatherItemViewModel::class.java)

        cityNameEditText = findViewById(R.id.add_weather_city_name)
        completeButton = findViewById(R.id.add_weather_done_btn)
        completeButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(button: View?) {
                val result = Intent()
                result.putExtra("city", cityNameEditText.text.trim().toString())
                setResult(Activity.RESULT_OK, result)
                finish()
            }
        })
    }
}
