package com.example.utrechtverkennen.ui

import androidx.lifecycle.ViewModel
import com.example.utrechtverkennen.data.DataSource
import com.example.utrechtverkennen.model.Location
import com.example.utrechtverkennen.model.LocationCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class CityUiState(
    val selectedLocationCategory: LocationCategory? = null,
    val filteredLocations: List<Location> = emptyList(),
    val selectedLocation: Location? = null,
)

class CityViewModel: ViewModel() {
    private var _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState

    init {
        initializeUiState()
    }

    fun initializeUiState() {
        setLocationCategory(LocationCategory.entries.first())
    }

    /*
    * If the argument 'specificLocation' is omitted, the first location with the
    * given locationType is used.
    * */
    fun setLocationCategory(locationCategory: LocationCategory, specificLocation: Location? = null) {
        val filteredLocations = DataSource.getLocationsByType(locationCategory)
        _uiState.update {
            it.copy(
                selectedLocationCategory = locationCategory,
                filteredLocations = filteredLocations,
                selectedLocation = specificLocation ?: filteredLocations.first(),
            )
        }
    }

    fun setLocation(location: Location) {
        setLocationCategory(location.type, specificLocation = location)
    }
}

