package com.example.labacomposeit_2_23.weatherReport

import com.example.labacomposeit_2_23.dataclasses.City
import com.example.labacomposeit_2_23.dataclasses.CurrentChosenCity
import com.example.labacomposeit_2_23.weatherReport.FindCoordinates
import com.example.labacomposeit_2_23.WeatherReportApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val token = "5ee412f55f8f6ac798dffba3b553a72c"
lateinit var currentCity: CurrentChosenCity

fun StartReport(cities: List<City>, city: String) {
    val chosencity = city
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Создаем реализацию API
    val weatherResponser = retrofit.create(WeatherReportApi::class.java)


    CoroutineScope(Dispatchers.IO).launch {
        try {
            val coord = FindCoordinates(cities, chosencity)
            val call = weatherResponser.getWeather(coord.first, coord.second, token)
            val temp = call.body()?.main?.temp

            currentCity = CurrentChosenCity(city, temp)
            println(currentCity)
        }
        catch (e: Exception) {

        }
    }
}

