package org.sopt.at.presentation.live.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.MainRoute

@Serializable
data object LiveRoute : MainRoute

fun NavController.navigateToLive(navOptions: NavOptions) {
    navigate(LiveRoute, navOptions)
}

//fun NavGraphBuilder.liveNavGraph() {
//    composable(LiveRoute.route) {
//        LiveScreen()
//    }
//}