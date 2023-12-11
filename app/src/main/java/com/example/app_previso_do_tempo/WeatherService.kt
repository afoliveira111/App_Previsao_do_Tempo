package com.example.app_previso_do_tempo

import android.app.DownloadManager
import android.telecom.Call
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Response
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
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                // Converte a resposta da API para um objeto WeatherData
                val weatherData = response.body?.string()?.let {
                    Gson().fromJson(it, WeatherData::class.java)
                }

                // Chama o callback com os dados do tempo
                callback(weatherData)
            }
        })