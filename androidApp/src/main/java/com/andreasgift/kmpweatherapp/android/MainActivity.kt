package com.andreasgift.kmpweatherapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andreasgift.kmpweatherapp.Greeting
import kotlin.text.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    WeatherView()
                }
            }
        }
    }
}

@Composable
fun WeatherView() {
    Column(modifier = Modifier.background(color = Color.Black)
        .padding(24.dp)) {
        Text("Montreal", style = MaterialTheme.typography.body1)
        Text("19°", style = MaterialTheme.typography.h2)
        Text("Clear sky", style = MaterialTheme.typography.body1)
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        WeatherView()
    }
}
