package org.sopt.at.presentation.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.MainRoute

@Serializable
data object HomeRoute : MainRoute

fun NavController.navigateToHome(navOptions: NavOptions) {
    navigate(HomeRoute, navOptions)
}

//fun NavGraphBuilder.homeNavGraph() {
//    composable<HomeRoute> {
//        HomeScreen()
//    }
//}