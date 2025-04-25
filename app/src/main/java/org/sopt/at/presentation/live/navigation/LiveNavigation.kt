package org.sopt.at.presentation.live.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.MainRoute
import org.sopt.at.presentation.live.LiveScreen

@Serializable
data object LiveRoute : MainRoute{
    override val route: String = "live"
}

fun NavController.navigateToLive(navOptions: NavOptions) {
    navigate(LiveRoute, navOptions)
}

//fun NavGraphBuilder.liveNavGraph() {
//    composable(LiveRoute.route) {
//        LiveScreen()
//    }
//}