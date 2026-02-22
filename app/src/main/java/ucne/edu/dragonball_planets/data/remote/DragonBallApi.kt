package ucne.edu.dragonball_planets.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ucne.edu.dragonball_planets.data.remote.dto.PlanetDto
import ucne.edu.dragonball_planets.data.remote.dto.PlanetResponseDto

interface DragonBallApi {

    @GET("planets")
    suspend fun getPlanets(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("name") name: String?
    ): Response<PlanetResponseDto>

    @GET("planets/{id}")
    suspend fun getPlanetDetail(
        @Path("id") id: Int
    ): Response<PlanetDto>
}
