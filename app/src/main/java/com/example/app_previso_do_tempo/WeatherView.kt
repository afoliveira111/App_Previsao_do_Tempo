package com.example.app_previso_do_tempo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.app_previso_do_tempo.databinding.WeatherViewBinding

class WeatherView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: WeatherViewBinding =
        WeatherViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun updateWeatherData(weatherData: WeatherData) {
        binding.weatherTitleTextView.text = weatherData.city
        binding.weatherTemperatureTextView.text = "${weatherData.temperature} °C"

        when (weatherData.condition) {
            "sunny" -> binding.conditionImageView.setImageResource(R.drawable.ic_sunny)
            "cloudy" -> binding.conditionImageView.setImageResource(R.drawable.ic_cloudy)
            "rainy" -> binding.conditionImageView.setImageResource(R.drawable.ic_rainy)
            else -> binding.conditionImageView.setImageResource(R.drawable.ic_unknown)
        }

        binding.weatherForecastTextView.text = weatherData.forecast
    }
}