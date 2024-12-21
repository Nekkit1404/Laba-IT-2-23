package com.example.labacomposeit_2_23.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.labacomposeit_2_23.dataclasses.City
import com.example.labacomposeit_2_23.weatherReport.StartReport
import com.example.labacomposeit_2_23.weatherReport.currentCity
import kotlin.math.round

@Composable
@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
fun weathereport_screen(cities: List<City>, back: () -> Unit) {
    val city = currentCity.city
    val temperature = currentCity.temperature
    FlowRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp)

    ) {
        Text(
            text = temperature?.let { round(it) }.toString() + "℃",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = city,
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier

                .fillMaxWidth()
        )
    }
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()

    ) {
        Button(
            onClick = { StartReport(cities, city) },
            modifier = Modifier
                .width(150.dp)
                .padding(bottom = 50.dp)
        ) {
            Text(
                text = "Обновить",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}