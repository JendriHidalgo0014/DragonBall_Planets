package ucne.edu.dragonball_planets.domain.repository

import ucne.edu.dragonball_planets.data.remote.Resource
import ucne.edu.dragonball_planets.domain.model.Planet


interface PlanetRepository {
    suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Resource<List<Planet>>

    suspend fun getPlanetDetail(id: Int): Resource<Planet>
}