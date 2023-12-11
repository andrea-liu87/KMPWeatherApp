package com.andreasgift.kmpweatherapp

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val ApplicationDispatcher: CoroutineDispatcher = Dispatchers.Default