package com.andreasgift.kmpweatherapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform