package com.example.weatherapp11.models

import com.google.gson.annotations.SerializedName

class Weather {

    @SerializedName("main")
    lateinit var mainDescription: String

    @SerializedName("description")
    lateinit var descrption: String

    @SerializedName("icon")
    lateinit var icon: String
}