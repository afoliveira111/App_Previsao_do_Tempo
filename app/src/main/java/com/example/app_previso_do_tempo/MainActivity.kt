package com.example.app_previso_do_tempo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app_previso_do_tempo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val weatherService = WeatherService()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


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
            weatherService.getWeatherData()
        } catch (e: Exception) {
            null
        }
    }

    private fun updateWeatherView(weatherData: WeatherData?) {
        weatherData?.let {
            // Atualiza a tela com os dados do tempo
            binding.titleTextView.text = it.city
            binding.temperatureTextView.text = "${it.temperature} °C"

            when (it.condition) {
                "sunny" -> binding.conditionImageView.setImageResource(R.drawable.ic_sunny)
                "cloudy" -> binding.conditionImageView.setImageResource(R.drawable.ic_cloudy)
                "rainy" -> binding.conditionImageView.setImageResource(R.drawable.ic_rainy)
                else -> binding.conditionImageView.setImageResource(R.drawable.ic_unknown)
            }

            binding.forecastTextView.text = it.forecast
        }
    }
}