package org.sopt.at.presentation.shorts.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.MainRoute

@Serializable
data object ShortsRoute : MainRoute

fun NavController.navigateToShorts(navOptions: NavOptions) {
    navigate(ShortsRoute, navOptions)
}


//fun NavGraphBuilder.shortsNavGraph() {
//    composable<ShortsRoute> {
//        ShortsScreen()
//    }
//}