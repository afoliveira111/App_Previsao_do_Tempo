package com.example.app_previso_do_tempo

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class WeatherService {

    private val BASE_URL = "api.openweathermap.org/data/2.5/weather?id=524901&appid=d30690f7a3f4a13123e3d39200ffb56c\n"
    private val API_KEY = "28a0e6bb47f7ccc4663efc0bbb3eee53"

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