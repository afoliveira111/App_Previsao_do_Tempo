package com.example.app_previso_do_tempo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val weatherService = WeatherService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicia o carregamento dos dados da API de tempo
        loadWeatherData()
    }

    private fun loadWeatherData() {
        GlobalScope.launch(Dispatchers.Main) {
            // Faz uma requisição HTTP para a API de tempo (usando suspensão)
            val weatherData = fetchWeatherData()

            // Atualiza a tela com os dados do tempo
            updateWeatherView(weatherData)
        }
    }

    private suspend fun fetchWeatherData(): WeatherData? {
        return try {
            // Utilize withContext para mudar o contexto de Dispatchers.IO para Dispatchers.Main
            withContext(Dispatchers.IO) {
                weatherService.getWeatherData()
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun updateWeatherView(weatherData: WeatherData?) {
        weatherData?.let {
            // Atualiza a tela com os dados do tempo
            titleTextView.text = it.city
            temperatureTextView.text = "${it.temperature} °C"

            when (it.condition) {
                "sunny" -> conditionImageView.setImageResource(R.drawable.ic_sunny)
                "cloudy" -> conditionImageView.setImageResource(R.drawable.ic_cloudy)
                "rainy" -> conditionImageView.setImageResource(R.drawable.ic_rainy)
                else -> conditionImageView.setImageResource(R.drawable.ic_unknown)
            }

            forecastTextView.text = it.forecast
        }
    }
}