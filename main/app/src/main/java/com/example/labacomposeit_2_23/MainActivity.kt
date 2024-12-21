package com.example.labacomposeit_2_23

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.labacomposeit_2_23.screens.search_screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/Stronger197/764f9886a1e8392ddcae2521437d5a3b/raw/65164ea1af958c75c81a7f0221bead610590448e/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val parserApi = retrofit.create(ParserApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            try {

                val cities = parserApi.getCityList()

                val sorted_list = mutableListOf<String>()
                for (city in cities) {
                    sorted_list.add(city.city)
                }
                sorted_list.sort()
                for (i in 0..3) {
                    sorted_list.removeAt(0)
                }



                runOnUiThread {
                    setContent {
                        SetUpScrollView( sorted_list, cities)
//                         LanguageButton(language = "RU")
                    }
                }

            }

            catch (e: Exception) {
                println("Невозможно соедениться с сервером")
            }
        }

    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun SetUpScrollView(sortedlist: MutableList<String>, cities: List<City>) {
    val NavController = rememberNavController()
    NavHost (
        navController = NavController,
        startDestination = "search_screen"

    )   {
        composable("search_screen") {
            search_screen( sortedlist = sortedlist, cities = cities) {
                NavController.navigate("weathereport_screen")

            }
        }
    }
}