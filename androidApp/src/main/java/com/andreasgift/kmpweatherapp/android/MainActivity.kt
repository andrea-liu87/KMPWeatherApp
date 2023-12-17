package com.andreasgift.kmpweatherapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andreasgift.kmpweatherapp.WeatherAPI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api = WeatherAPI()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    WeatherView(api)
                }
            }
        }
    }
}

@Composable
fun WeatherView(api: WeatherAPI) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.background), contentScale = ContentScale.FillBounds)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var name by remember { mutableStateOf("Montreal") }
            var temp by remember { mutableStateOf("19") }
            var descr by remember { mutableStateOf("Clear Sky") }
            var highest by remember { mutableStateOf("23") }
            var lowest by remember { mutableStateOf("12") }
            api.getWeatherAPIData(
                {
                    name = it.name ?: ""
                    temp = convertToC(it.main.temp ?: 0.00)
                    descr = it.weather[0].description ?: ""
                    highest = convertToC(it.main.tempMax ?: 0.00)
                    lowest = convertToC(it.main.tempMin ?: 0.00)
                },
                {
                    name = it
                    temp = ""
                    descr = ""
                }
            )
            Text(name, style = MaterialTheme.typography.h3)
            Text("$temp° C", style = MaterialTheme.typography.h2)
            Text(descr, style = MaterialTheme.typography.body1, color = Color.Gray)
            Text("H:$highest° L:$lowest°", style = MaterialTheme.typography.body2)
            Image(
                modifier = Modifier.padding(vertical = 12.dp),
                painter = painterResource(id = R.drawable.house), contentDescription = "house"
            )
        }
    }
}

fun convertToC (f:Double) : String {
    val tempInC = f - 273
    return String.format("%.0f", tempInC)
}

@Composable
fun ErrorWeatherView(errorMessage: String) {
    Column(
        modifier = Modifier
            .background(color = Color.Black)
            .padding(24.dp)
    ) {
        Text(errorMessage, style = MaterialTheme.typography.body1, color = Color.White)
    }
}

@Composable
fun LoadingWeatherView() {
    Column(
        modifier = Modifier
            .background(color = Color.Black)
            .padding(24.dp)
    ) {
        Text("Loading ...", style = MaterialTheme.typography.body1, color = Color.White)
    }
}

@Preview (showSystemUi = true, device = Devices.NEXUS_10)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        WeatherView(WeatherAPI())
    }
}
