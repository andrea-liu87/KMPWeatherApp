package com.andreasgift.kmpweatherapp.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreasgift.kmpweatherapp.WeatherAPIResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface WeatherUiState {

    val isLoading: Boolean
    val errorMessages: String

    /**
     * There are no data.
     *
     * This could either be because they are still loading or they failed to load, and we are
     * waiting to reload them.
     */
    data class NoData(
        override val isLoading: Boolean,
        override val errorMessages: String,
    ) : WeatherUiState

    /**
     * Succesffuly get data from API
     */
    data class APIReturnData(
        val data: WeatherAPIResponse,
        override val isLoading: Boolean,
        override val errorMessages: String,
    ) : WeatherUiState
}

/**
 * An internal representation of the Home route state, in a raw form
 */
private data class WeatherViewModelState(
    val data: WeatherAPIResponse? = null,
    val isLoading: Boolean = false,
    val errorMessages: String = "",
    //val searchInput: String = "",
) {

    /**
     * Converts this [WeatherViewModelState] into a more strongly typed [WeatherUiState] for driving
     * the ui.
     */
    fun toUiState(): WeatherUiState =
        if (data == null) {
            WeatherUiState.NoData(
                isLoading = isLoading,
                errorMessages = errorMessages,
                //searchInput = searchInput
            )
        } else {
            WeatherUiState.APIReturnData(
                data = data,
                isLoading = isLoading,
                errorMessages = errorMessages,
                //searchInput = searchInput
            )
        }
}

/**
 * ViewModel that handles the business logic of the Home screen
 */
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repo: WeatherRepository,
) : ViewModel() {

    private val viewModelState = MutableStateFlow(
        WeatherViewModelState(
            isLoading = true
        )
    )

    // UI state exposed to the UI
    val uiState = viewModelState
        .map(WeatherViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        refreshData()
    }

    private fun refreshData() {
        // Ui state is refreshing
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = repo.getWeatherData()
            viewModelState.update {
                when (result) {
                    is Result.Success -> it.copy(data = result.data, isLoading = false)
                    is Result.Error -> {
                        val errorMessages = it.errorMessages
                        it.copy(errorMessages = errorMessages, isLoading = false)
                    }
                }
            }
        }
    }

}
