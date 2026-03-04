package com.example.evbuddy

import androidx.lifecycle.ViewModel
import com.example.evbuddy.data.FixedCharger
import com.example.evbuddy.data.MobileDriver
import com.example.evbuddy.data.MockDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeUiState(
    val mobileDrivers: List<MobileDriver> = emptyList(),
    val fixedChargers: List<FixedCharger> = emptyList(),
    val showDriverSheet: Boolean = false,
    val showChargerSheet: Boolean = false,
    val selectedDriver: MobileDriver? = null
)

class EVBuddyViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onFindMobileDriverClicked() {
        _uiState.value = _uiState.value.copy(
            showDriverSheet = true,
            mobileDrivers = MockDataRepository.mobileDrivers
        )
    }

    fun onFindFixedChargerClicked() {
        _uiState.value = _uiState.value.copy(
            showChargerSheet = true,
            fixedChargers = MockDataRepository.fixedChargers
        )
    }

    fun onDriverSelected(driver: MobileDriver) {
        _uiState.value = _uiState.value.copy(selectedDriver = driver)
    }

    fun dismissDriverSheet() {
        _uiState.value = _uiState.value.copy(showDriverSheet = false, selectedDriver = null)
    }

    fun dismissChargerSheet() {
        _uiState.value = _uiState.value.copy(showChargerSheet = false)
    }
}
