package com.example.labacomposeit_2_23.weatherReport

import com.example.labacomposeit_2_23.dataclasses.City

fun FindCoordinates(cities: List<City>, city: String): Pair<String, String> {
    val lat: String
    val lon: String
    for (cityData in cities) {
        if (cityData.city == city) {
            lat = cityData.latitude
            lon = cityData.longitude
            return Pair(lat, lon)
        }
    }
    return Pair("", "")
}