package com.example.app_previso_do_tempo

import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {
    var city: String = ""
    var temperature: String = ""
    var conditionIcon: Int = 0
    var forecast: String = ""
}