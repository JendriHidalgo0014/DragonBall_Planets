package ucne.edu.dragonball_planets.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ucne.edu.dragonball_planets.presentation.tareas.details.DetailPlanetScreen
import ucne.edu.dragonball_planets.presentation.tareas.list.ListPlanetScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.PlanetList
    ) {
        composable<Screen.PlanetList> {
            ListPlanetScreen(
                onPlanetClick = { planetId ->
                    navHostController.navigate(Screen.PlanetDetail(planetId))
                }
            )
        }

        composable<Screen.PlanetDetail> {
            DetailPlanetScreen(
                onBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}