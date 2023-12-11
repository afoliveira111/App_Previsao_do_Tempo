package com.example.app_previso_do_tempo

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class WeatherService {

    private val BASE_URL = "https://api.openweathermap.org/data/2.5/weather"
    private val API_KEY = "YOUR_API_KEY"

    suspend fun getWeatherData(): WeatherData? {
        return withContext(Dispatchers.IO) {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("$BASE_URL?q=SAO_PAULO,BR&appid=$API_KEY")
                    .build()

                val response = client.newCall(request).execute()
                val jsonString = response.body?.string()

                Gson().fromJson(jsonString, WeatherData::class.java)
            } catch (e: IOException) {
                null
            }
        }
    }
}