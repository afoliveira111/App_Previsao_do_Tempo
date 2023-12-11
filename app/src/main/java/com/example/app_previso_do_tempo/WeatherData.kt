package com.example.app_previso_do_tempo

data class WeatherData(
    val city: String,
    val temperature: Double,
    val condition: String,
    val forecast: String
)