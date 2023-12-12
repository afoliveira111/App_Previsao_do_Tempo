package com.example.app_previso_do_tempo

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Interface para definir os endpoints da API
interface WeatherApiService {
    @GET("weather")
    fun getWeatherData(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): Call<WeatherData>
}

class WeatherService {
    private val apiKey = "d30690f7a3f4a13123e3d39200ffb56c"
    private val baseUrl = "https://api.openweathermap.org/data/2.5/forecast?q=Brasil,BRA&appid=583f803dfc6a7f2f96ff9957c330c2b0&units=imperial"

    // Configuração do Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Criação do serviço da API
    private val weatherApiService: WeatherApiService = retrofit.create(WeatherApiService::class.java)

    fun getWeatherData(city: String): WeatherData? {
        // Fazendo a solicitação à API usando o Retrofit
        val call = weatherApiService.getWeatherData(city, apiKey)

        // Executa a chamada de forma síncrona (para simplificar, em um aplicativo real, use corrotinas ou chamadas assíncronas)
        val response = call.execute()

        return if (response.isSuccessful) {
            // Retorna os dados da resposta se a chamada for bem-sucedida
            response.body()
        } else {
            // Em caso de erro, imprima o código de erro e retorne nulo
            println("Error: ${response.code()}")
            null
        }
    }
}