package com.example.app_previso_do_tempo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.app_previso_do_tempo.databinding.WeatherViewBinding

class WeatherView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: WeatherViewBinding =
        WeatherViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun updateWeatherData(weatherData: WeatherData) {
        // Atualize o ViewModel com os dados recebidos
        val viewModel = WeatherViewModel()
        viewModel.city = weatherData.city
        viewModel.temperature = "${weatherData.temperature} °C"
        viewModel.conditionIcon = getConditionIcon(weatherData.condition)
        viewModel.forecast = weatherData.forecast

        // Vincule o ViewModel ao layout usando o Data Binding
        binding.viewModel = viewModel
    }

    fun getConditionIcon(condition: String): Int {
        return when (condition.toLowerCase()) {
            "clear" -> R.drawable.ic_sunny
            "clouds" -> R.drawable.ic_cloudy
            "rain" -> R.drawable.ic_rainy
            else -> R.drawable.ic_unknown
        }
    }
}