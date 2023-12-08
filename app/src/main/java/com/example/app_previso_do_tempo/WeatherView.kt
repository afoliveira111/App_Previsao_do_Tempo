package com.example.app_previso_do_tempo

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class WeatherView : ConstraintLayout {

    // Declara os atributos da tela
    private lateinit var titleTextView: TextView
    private lateinit var temperatureTextView: TextView
    private lateinit var conditionImageView: ImageView
    private lateinit var forecastTextView: TextView

    // Cria os elementos visuais da tela
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Declara os elementos visuais da tela
        titleTextView = findViewById(R.id.title_text_view)
        temperatureTextView = findViewById(R.id.temperature_text_view)
        conditionImageView = findViewById(R.id.condition_image_view)
        forecastTextView = findViewById(R.id.forecast_text_view)

        // Define os atributos dos elementos visuais da tela
        titleTextView.text = "Clima"
        temperatureTextView.text = "-ºC"
        conditionImageView.setImageResource(R.drawable.ic_sunny)
        forecastTextView.text = "Céu claro"
    }

    // Atualiza a tela com os dados do tempo
    fun updateWeatherData(weatherData: WeatherData) {
        // Atualiza o título da tela
        titleTextView.text = weatherData.city

        // Atualiza a temperatura da tela
        temperatureTextView.text = weatherData.temperature.toString()

        // Atualiza a condição climática da tela
        conditionImageView.setImageResource(
            when (weatherData.condition) {
                "sunny" -> R.drawable.ic_sunny
                "cloudy" -> R.drawable.ic_cloudy
                "rainy" -> R.drawable.ic_rainy
                else -> R.drawable.ic_unknown
            }
        )

        // Atualiza a previsão do tempo da tela
        forecastTextView.text = weatherData.forecast
    }
}