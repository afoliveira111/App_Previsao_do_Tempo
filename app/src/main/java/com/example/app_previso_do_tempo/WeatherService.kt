package com.example.app_previso_do_tempo

class WeatherService {

    private val BASE_URL = "https://api.openweathermap.org/data/2.5/weather"
    private val API_KEY = "YOUR_API_KEY"

    fun getWeatherData(callback: (WeatherData?) -> Unit) {
        // Implementação para obter dados do tempo da API
        // ...

        // Exemplo simples, retornando dados fictícios para fins de demonstração
        val fakeData = WeatherData("São Paulo", 25.0, "sunny", "Céu claro")
        callback(fakeData)
    }
}