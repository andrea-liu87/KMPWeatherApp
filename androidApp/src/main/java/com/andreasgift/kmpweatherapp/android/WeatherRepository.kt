package com.andreasgift.kmpweatherapp.android

import android.util.Log
import com.andreasgift.kmpweatherapp.WeatherAPI
import com.andreasgift.kmpweatherapp.WeatherAPIResponse
import javax.inject.Inject

/**
 * Interface to the Posts data layer.
 */
interface WeatherRepository {
    suspend fun getWeatherData(): Result<WeatherAPIResponse>
    /**
     * Get weather with specific longitude & latitude.
     */
    suspend fun getWeatherBaseLatLong(latitude:Double, longitude:Double): Result<WeatherAPIResponse>

    /**
     * Get weather with specific place.
     */
    suspend fun getWeatherBasePlace(place: String): Result<WeatherAPIResponse>
}

class WeatherRepositoryImpl @Inject constructor() : WeatherRepository {
    override suspend fun getWeatherData(): Result<WeatherAPIResponse> {
        var response: Result<WeatherAPIResponse> = Result.Error("Loading")
        WeatherAPI().getWeatherAPIData(
            { response = Result.Success(it) },
            {
                response = Result.Error(it)
            }
        )
        Log.d("DATA", response.toString())
        return response
    }

    override suspend fun getWeatherBaseLatLong(
        latitude: Double,
        longitude: Double
    ): Result<WeatherAPIResponse> {
        var response: Result<WeatherAPIResponse> = Result.Error("Loading")
        WeatherAPI().getWeatherAPIDataLatLon(
            latitude = latitude,
            longitude = longitude,
            { response = Result.Success(it) },
            {
                response = Result.Error(it)
            }
        )
        return response
    }

    override suspend fun getWeatherBasePlace(place: String): Result<WeatherAPIResponse> {
        var response: Result<WeatherAPIResponse> = Result.Error("Loading")
        WeatherAPI().getWeatherAPIDataPlace(
            place = place,
            { response = Result.Success(it) },
            {
                response = Result.Error(it)
            }
        )
        return response
    }

}