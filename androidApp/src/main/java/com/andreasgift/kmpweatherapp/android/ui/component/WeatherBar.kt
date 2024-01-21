package com.andreasgift.kmpweatherapp.android.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreasgift.kmpweatherapp.android.ui.theme.SolidPurple

@Composable
fun WeatherBar(){
    Column(
        modifier = Modifier
            .background(color = SolidPurple, shape = RoundedCornerShape(30.dp))
            .padding(vertical = 16.dp, horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("12 AM", style = TextStyle(color = androidx.compose.ui.graphics.Color.White, fontSize = 20.sp))
        Image(painter = painterResource(id = android.R.drawable.arrow_up_float), contentDescription = "")
        Text("19°",style = TextStyle(color = androidx.compose.ui.graphics.Color.White, fontSize = 24.sp))
    }
}

@Composable
fun HourForecast(){
    LazyRow(
        modifier = Modifier.padding(vertical = 8.dp),
    horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        items(24) { weatherData ->
            WeatherBar()
        }
    }
}

@Preview
@Composable
fun PreviewWeatherBarView(){
    HourForecast()
}