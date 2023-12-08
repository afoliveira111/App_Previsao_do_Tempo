package com.example.app_previso_do_tempo

import android.app.DownloadManager
import android.telecom.Call
import java.io.IOException
import javax.security.auth.callback.Callback

class WeatherService {

    private val BASE_URL = "https://api.openweathermap.org/data/2.5/weather"
    private val API_KEY = "YOUR_API_KEY"

    fun getWeatherData(callback: (WeatherData) -> Unit) {
        // Cria uma instância do objeto OkHttpClient
        val client = OkHttpClient()

        // Cria uma instância do objeto Request
        val request = DownloadManager.Request.Builder()
            .url(BASE_URL)
            .addQueryParameter("q", "SAO_PAULO,BR")
            .addQueryParameter("appid", API_KEY)
            .build()

        // Faz uma requisição HTTP para a API de tempo
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                // Converte a resposta da API para um objeto WeatherData
                val weatherData = response.body?.string()?.let {
                    Gson().fromJson(it, WeatherData::class.java)
                }

                // Chama o callback com os dados do tempo
                callback(weatherData)
            }