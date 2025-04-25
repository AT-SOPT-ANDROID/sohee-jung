package org.sopt.at.presentation.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.MainRoute
import org.sopt.at.presentation.home.HomeScreen

@Serializable
data object HomeRoute: MainRoute{
    override val route: String = "home"
}

fun NavController.navigateToHome(navOptions: NavOptions) {
    navigate(HomeRoute, navOptions)
}

//fun NavGraphBuilder.homeNavGraph() {
//    composable<HomeRoute> {
//        HomeScreen()
//    }
//}