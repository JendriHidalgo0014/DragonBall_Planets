package ucne.edu.dragonball_planets.presentation.tareas.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ucne.edu.dragonball_planets.data.remote.Resource
import ucne.edu.dragonball_planets.domain.usecase.GetPlanetsUseCase
import javax.inject.Inject

@HiltViewModel
class ListPlanetViewModel @Inject constructor(
    private val getPlanetsUseCase: GetPlanetsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(ListPlanetUiState())
    val state = _state.asStateFlow()

    init {
        loadPlanets()
    }

    fun onEvent(event: ListPlanetEvent) {
        when (event) {
            is ListPlanetEvent.UpdateFilterName -> {
                _state.update {
                    it.copy(filterName = event.name)
                }
            }
            ListPlanetEvent.Search -> loadPlanets()
        }
    }

    private fun loadPlanets() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val current = _state.value

            val result = getPlanetsUseCase(
                name = current.filterName.takeIf { it.isNotBlank() }
            )

            when (result) {
                is Resource.Success ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            planets = result.data ?: emptyList()
                        )
                    }

                is Resource.Error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }

                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
            }
        }
    }
}