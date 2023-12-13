package com.example.app_previso_do_tempo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.app_previso_do_tempo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        // Utilize corrotinas para fazer a chamada de rede em uma thread separada
        GlobalScope.launch(Dispatchers.Main) {
            try {
                // Faz a chamada de rede em uma corrotina com o contexto IO
                val weatherData = withContext(Dispatchers.IO) {
                    weatherService.getWeatherData(city)
                }

                // Atualiza a tela com os dados do tempo
                updateWeatherView(weatherData)
            } catch (e: Exception) {
                // Trata erros de requisição aqui
                Log.e("WeatherApp", "Error loading weather data: ${e.message}", e)
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
            binding.conditionImageView.setImageResource(getConditionIcon(it.condition))
            binding.forecastTextView.text = viewModel.forecast
        }
    }

    private fun getConditionIcon(condition: String): Int {
       return when (condition) {
            "sunny" -> R.drawable.ic_sunny
            "cloudy" -> R.drawable.ic_cloudy
            "rainy" -> R.drawable.ic_rainy
            "snowy" -> R.drawable.ic_sunny
            else -> R.drawable.ic_unknown
        }
    }

}