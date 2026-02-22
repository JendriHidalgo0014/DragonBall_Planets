package ucne.edu.dragonball_planets.presentation.tareas.details

import ucne.edu.dragonball_planets.domain.model.Planet

data class DetailPlanetUiState(
    val isLoading: Boolean = false,
    val planet: Planet? = null,
    val error: String? = null
)