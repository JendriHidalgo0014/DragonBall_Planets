package ucne.edu.dragonball_planets.data.repository

import ucne.edu.dragonball_planets.data.remote.DragonBallApi
import ucne.edu.dragonball_planets.data.remote.Resource
import ucne.edu.dragonball_planets.domain.model.Planet
import ucne.edu.dragonball_planets.domain.repository.PlanetRepository
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val api: DragonBallApi
) : PlanetRepository {

    override suspend fun getPlanets(
        page: Int,
        limit: Int,
        name: String?
    ): Resource<List<Planet>> {
        return try {
            val response = api.getPlanets(page, limit, name)

            if (response.isSuccessful && response.body() != null) {
                val data = response.body()!!.items.map { it.toDomain() }
                Resource.Success(data)
            } else {
                Resource.Error("Error servidor: ${response.message()}")
            }
        } catch (e: Exception) {
            Resource.Error("Error conexión: ${e.localizedMessage}")
        }
    }

    override suspend fun getPlanetDetail(id: Int): Resource<Planet> {
        return try {
            val response = api.getPlanetDetail(id)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!.toDomain())
            } else {
                Resource.Error("Planeta no encontrado")
            }
        } catch (e: Exception) {
            Resource.Error("Error: ${e.message}")
        }
    }
}