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
    private val viewModel = WeatherViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cityNameEditText = binding.cityNameEditText
        val searchButton = binding.searchButton

        searchButton.setOnClickListener {
            val cityName = cityNameEditText.text.toString()
            if (cityName.isNotBlank()) {
                // Inicia o carregamento dos dados da API de tempo
                loadWeatherData(cityName)
            } else {
                // Trate o caso em que o campo da cidade está vazio
            }
        }
    }

    private fun loadWeatherData(city: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                // Faz uma requisição HTTP para a API de tempo (usando suspensão)
                val weatherData = weatherService.getWeatherData(city)

                // Atualiza a tela com os dados do tempo
                updateWeatherView(weatherData)
            } catch (e: Exception) {
                // Trate erros de requisição aqui
            }
        }
    }

    private fun updateWeatherView(weatherData: WeatherData?) {
        weatherData?.let {
            // Atualize o ViewModel com os dados recebidos
            viewModel.city = it.city
            viewModel.temperature = "${it.temperature} °C"
            viewModel.conditionIcon = getConditionIcon(it.condition)
            viewModel.forecast = it.forecast

            // Atualize a tela com os dados do ViewModel
            binding.titleTextView.text = viewModel.city
            binding.temperatureTextView.text = viewModel.temperature
            binding.conditionImageView.setImageResource(viewModel.conditionIcon)
            binding.forecastTextView.text = viewModel.forecast
        }
    }


}