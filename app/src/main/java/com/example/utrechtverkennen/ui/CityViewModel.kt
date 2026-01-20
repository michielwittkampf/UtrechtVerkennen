package com.example.utrechtontdekker.ui

import com.example.utrechtontdekker.data.DataSource
import com.example.utrechtontdekker.model.Location
import com.example.utrechtontdekker.model.LocationType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class CityUiState(
    val selectedLocationType: LocationType? = null,
    val filteredLocations: List<Location> = emptyList(),
    val selectedLocation: Location? = null,
)

class CityViewModel() {
    private var _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState

    init {
        initializeUiState()
    }

    fun initializeUiState() {
        setLocationType(LocationType.entries.first())
    }

    /*
    * If the argument 'specificLocation' is omitted, the first location with the
    * given locationType is used.
    * */
    fun setLocationType(locationType: LocationType, specificLocation: Location? = null) {
        val filteredLocations = DataSource.getLocationsByType(locationType)
        _uiState.update {
            it.copy(
                selectedLocationType = locationType,
                filteredLocations = filteredLocations,
                selectedLocation = specificLocation ?: filteredLocations.first(),
            )
        }
    }

    fun setLocation(location: Location) {
        setLocationType(location.type, specificLocation = location)
    }
}

