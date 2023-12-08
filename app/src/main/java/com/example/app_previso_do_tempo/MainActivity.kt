package com.example.app_previso_do_tempo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtém o objeto WeatherView
        val weatherView = findViewById<WeatherView>(R.id.weather_view)

        // Inicia o carregamento dos dados da API de tempo
        loadWeatherData()
    }

    private fun loadWeatherData() {
        // Cria uma instância da classe WeatherService
        val weatherService = WeatherService()

        // Faz uma requisição HTTP para a API de tempo
        weatherService.getWeatherData { weatherData ->
            // Atualiza a tela com os dados do tempo
            weatherView.updateWeatherData(weatherData)
        }
    }
}