package ucne.edu.dragonball_planets.presentation.tareas.list

import ucne.edu.dragonball_planets.domain.model.Planet

data class ListPlanetUiState(
    val isLoading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val error: String? = null,
    val filterName: String = ""
)